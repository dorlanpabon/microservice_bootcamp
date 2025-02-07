package com.pragma.powerup.infrastructure.output.jpa.mapper;

import com.pragma.powerup.domain.model.Bootcamp;
import com.pragma.powerup.infrastructure.output.jpa.entity.BootcampEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IBootcampEntityMapper {

    BootcampEntity toEntity(Bootcamp tecnology);
    Bootcamp toBootcamp(BootcampEntity tecnologyEntity);
}