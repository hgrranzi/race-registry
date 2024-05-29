package com.pmu.race.registry.repository;

import com.pmu.race.registry.model.entity.HorseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HorseRepository extends JpaRepository<HorseEntity, UUID> {
}
