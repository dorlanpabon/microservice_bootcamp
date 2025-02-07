package com.pragma.powerup.infrastructure.entrypoints.mapper;

import com.pragma.powerup.infrastructure.entrypoints.dto.request.BootcampRequestDto;
import com.pragma.powerup.domain.model.Bootcamp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBootcampRequestMapper {

    @Mapping(target = "capacityCount", expression = "java(bootcampRequestDto.getCapacities().size())")
    Bootcamp toBootcamp(BootcampRequestDto bootcampRequestDto);

}
