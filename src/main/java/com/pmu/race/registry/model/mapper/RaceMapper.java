package com.pmu.race.registry.model.mapper;

import com.pmu.race.registry.model.dto.RaceDto;
import com.pmu.race.registry.model.entity.RaceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface RaceMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "number", target = "number"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "horses", target = "horses")
    })
    RaceEntity toEntity(RaceDto dto);

}
