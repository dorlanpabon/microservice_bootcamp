package com.pragma.powerup.domain.model;

import java.util.List;

public class Bootcamp {

    private Long id;
    private String name;
    private String description;
    private List<Long> capacities;
    private List<Capacity> capacityList;
    private Integer capacityCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getCapacities() {
        return capacities;
    }

    public void setCapacities(List<Long> capacities) {
        this.capacities = capacities;
    }

    public List<Capacity> getCapacityList() {
        return capacityList;
    }

    public void setCapacityList(List<Capacity> capacityList) {
        this.capacityList = capacityList;
    }

    public Integer getCapacityCount() {
        return capacityCount;
    }

    public void setCapacityCount(Integer capacityCount) {
        this.capacityCount = capacityCount;
    }
}
