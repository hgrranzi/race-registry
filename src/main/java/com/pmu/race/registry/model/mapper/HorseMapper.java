package com.pmu.race.registry.model.mapper;

import com.pmu.race.registry.model.dto.HorseDto;
import com.pmu.race.registry.model.entity.HorseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HorseMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "number", target = "number"),
            @Mapping(target = "race_id", ignore = true)
    })
    HorseEntity toEntity(HorseDto dto);

    List<HorseEntity> map(List<HorseDto> dtoList);
}
