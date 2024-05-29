package com.pmu.race.registry.model.dto;

import com.pmu.race.registry.model.validation.ValidHorses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class RaceDto {

    private LocalDate date;

    @Positive
    private Integer number;

    @NotBlank
    private String name;

    @ValidHorses
    @Valid
    private List<HorseDto> horses;

}
