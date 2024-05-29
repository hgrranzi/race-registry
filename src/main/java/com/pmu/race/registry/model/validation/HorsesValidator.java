package com.pmu.race.registry.model.validation;

import com.pmu.race.registry.model.dto.HorseDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class HorsesValidator implements ConstraintValidator<ValidHorses, List<HorseDto>> {

    @Override
    public boolean isValid(List<HorseDto> horses, ConstraintValidatorContext context) {
        if (horses == null || horses.size() < 3) {
            return false;
        }
        List<Integer> numbers = horses.stream().map(HorseDto::getNumber).sorted().toList();
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) != i + 1) {
                return false;
            }
        }
        return true;
    }
}
