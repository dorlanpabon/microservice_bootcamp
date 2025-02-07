package com.pragma.powerup.infrastructure.entrypoints.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Getter
@Setter
public class BootcampPageRequestDto {

    @Schema(description = "Page number", example = "0")
    @NotNull
    @Min(0)
    private Integer page;

    @Schema(description = "Number of elements per page", example = "10")
    @NotNull
    @Positive
    @Min(1)
    private Integer size;

    @Schema(description = "Sort direction", example = "ASC")
    @NotNull
    @Pattern(regexp = "ASC|DESC")
    private String direction;

    @Schema(description = "Sort field", example = "name")
    @NotNull
    @Pattern(regexp = "name|capacityCount")
    private String field;

}
