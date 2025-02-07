package com.pragma.powerup.infrastructure.entrypoints.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class BootcampRequestDto {

    @Schema(description = "Bootcamp name", example = "Desarrollador backend", required = true)
    @Size(max = 50, message = "Bootcamp name must have a maximum of 50 characters")
    @NotNull(message = "Bootcamp name is required")
    private String name;

    @Schema(description = "Bootcamp description", example = "Bootcamp para desarrolladores backend", required = true)
    @Size(max = 100, message = "Bootcamp description must have a maximum of 100 characters")
    @NotNull(message = "Bootcamp description is required")
    private String description;

    @Schema(description = "Lista de IDs de capacidades, minimo 3 y máximo 20 elementos", example = "[1, 2, 3]", required = true)
    @Size(min=1, max = 4, message = "La lista de capacidades debe contener como minimo 1 y máximo 4 elementos")
    @UniqueElements(message = "La lista de tecnologías no puede contener elementos duplicados")
    private List<Long> capacities;

}
