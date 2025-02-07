package com.pragma.powerup.infrastructure.entrypoints.mapper;

import com.pragma.powerup.infrastructure.entrypoints.dto.response.BootcampResponseDto;
import com.pragma.powerup.domain.model.Bootcamp;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBootcampResponseMapper {

    BootcampResponseDto toBootcampResponseDto(Bootcamp bootcamp);

}
