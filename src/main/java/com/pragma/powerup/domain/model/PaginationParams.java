package com.pragma.powerup.domain.model;

public class PaginationParams {
    private Integer page;
    private Integer size;
    private String direction;
    private String field;

    public PaginationParams(Integer page, Integer size, String direction, String field) {
        this.page = page;
        this.size = size;
        this.direction = direction;
        this.field = field;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
