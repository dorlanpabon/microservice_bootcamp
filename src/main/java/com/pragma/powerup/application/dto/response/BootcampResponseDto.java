package com.pragma.powerup.application.dto.response;

import com.pragma.powerup.domain.model.Capacity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BootcampResponseDto {
    private Long id;
    private String name;
    private String description;
    private List<Capacity> capacityList;
}
