package com.pmu.race.registry.service;

import com.pmu.race.registry.event.EventProducer;
import com.pmu.race.registry.model.dto.RaceDto;
import com.pmu.race.registry.model.entity.RaceEntity;
import com.pmu.race.registry.model.mapper.RaceMapper;
import com.pmu.race.registry.repository.RaceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RegistryServiceTest {

    @Mock
    private RaceRepository raceRepository;

    @Mock
    private RaceMapper raceMapper;

    @Mock
    private EventProducer eventProducer;

    @InjectMocks
    private RegistryService registryService;

    private RaceDto raceDto;

    private RaceEntity raceEntity;

    @BeforeEach
    public void setup() {
        raceDto = new RaceDto();
        raceDto.setDate(LocalDate.now());
        raceDto.setName("Test Race");
        raceDto.setNumber(1);

        raceEntity = new RaceEntity();
        raceEntity.setDate(raceDto.getDate());
        raceEntity.setName(raceDto.getName());
        raceEntity.setNumber(raceDto.getNumber());

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateRaceSuccess() {
        when(raceMapper.toEntity(raceDto)).thenReturn(raceEntity);
        when(raceRepository.findByDateAndNameOrNumber(any(LocalDate.class), anyString(), anyInt()))
                .thenReturn(Collections.emptyList());

        registryService.createRace(raceDto);

        verify(raceRepository, times(1)).save(raceEntity);
        verify(eventProducer, times(1)).sendEvent(raceDto);
    }

    @Test
    public void testCreateRaceExceptionRaceNotUnique() {
        when(raceMapper.toEntity(raceDto)).thenReturn(raceEntity);
        when(raceRepository.findByDateAndNameOrNumber(any(LocalDate.class), anyString(), anyInt()))
                .thenReturn(List.of(raceEntity));

        assertThrows(IllegalStateException.class, () -> registryService.createRace(raceDto));

        verify(raceRepository, never()).save(any(RaceEntity.class));
        verify(eventProducer, never()).sendEvent(any(RaceDto.class));
    }
}
