package com.pmu.race.registry.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class HorseDto {

    @Positive
    private Integer number;

    @NotBlank
    private String name;
}
