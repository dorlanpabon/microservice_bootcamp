package com.pragma.powerup.domain.constants;

public class DomainConstants {
    public static final String NAME_MUST_BE_LESS_THAN_50_CHARACTERS = "Name must be less than 50 characters";
    public static final String DESCRIPTION_MUST_BE_LESS_THAN_90_CHARACTERS = "Description must be less than 90 characters";
    public static final String BOOTCAMP_ALREADY_EXISTS = "Bootcamp already exists";
    public static final String EMPTY_CAPACITY_LIST = "Capacity list cannot be empty";
    public static final int MIN_TECHNOLOGIES = 1;
    public static final int MAX_TECHNOLOGIES = 3;
    public static final String INVALID_CAPACITY_COUNT = "Invalid number of technologies";
    public static final String INVALID_PAGINATION_PARAMS = "Invalid pagination params";
    public static final String INVALID_SORT_FIELD = "Invalid sort field";
    public static final String INVALID_SORT_DIRECTION = "Invalid sort direction";

    DomainConstants() {
        throw new IllegalStateException("Utility class");
    }
}
