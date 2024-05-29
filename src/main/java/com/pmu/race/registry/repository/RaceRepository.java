package com.pmu.race.registry.repository;

import com.pmu.race.registry.model.entity.RaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface RaceRepository extends JpaRepository<RaceEntity, UUID> {

    @Query("SELECT r FROM RaceEntity r WHERE r.date = :date AND (r.name = :name OR r.number = :number)")
    List<RaceEntity> findByDateAndNameOrNumber(@Param("date") LocalDate date, @Param("name") String name, @Param("number") int number);
}
