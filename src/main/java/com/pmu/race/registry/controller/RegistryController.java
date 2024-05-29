package com.pmu.race.registry.controller;

import com.pmu.race.registry.model.dto.RaceDto;
import com.pmu.race.registry.service.RegistryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("registry/api/v1")
@RequiredArgsConstructor
public class RegistryController {

    private final RegistryService registryService;

    @PostMapping("/race")
    public ResponseEntity<String> createRace(@RequestBody @Valid RaceDto race) {
        registryService.createRace(race);
        return ResponseEntity.ok("Race created.");
    }

    @ExceptionHandler({IllegalStateException.class, MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<String> handleException() {
        return new ResponseEntity<>("Bad request.", HttpStatus.BAD_REQUEST);
    }
}
