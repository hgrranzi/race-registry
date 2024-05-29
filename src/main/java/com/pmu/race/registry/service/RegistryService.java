package com.pmu.race.registry.service;

import com.pmu.race.registry.event.EventProducer;
import com.pmu.race.registry.model.dto.RaceDto;
import com.pmu.race.registry.model.entity.RaceEntity;
import com.pmu.race.registry.model.mapper.RaceMapper;
import com.pmu.race.registry.repository.RaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistryService {

    private final RaceRepository raceRepository;

    private final RaceMapper raceMapper;

    private final EventProducer eventProducer;

    @Transactional
    public void createRace(RaceDto race) {
        RaceEntity raceEntity = raceMapper.toEntity(race);
        checkRaceUniqueness(raceEntity);
        raceRepository.save(raceEntity);
        eventProducer.sendEvent(race);
    }

    private void checkRaceUniqueness(RaceEntity raceEntity) {
        List<RaceEntity> sameDateRaces = raceRepository.findByDateAndNameOrNumber(raceEntity.getDate(), raceEntity.getName(), raceEntity.getNumber());
        if (!sameDateRaces.isEmpty()) {
            throw new IllegalStateException("A race with the same name or number already exists for the date: " + raceEntity.getDate());
        }
    }
}
