package com.pmu.race.registry.model.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = HorsesValidator.class)
public @interface ValidHorses {
    String message() default "There must be at least 3 horses with consecutive numbers without duplicates or gaps.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
