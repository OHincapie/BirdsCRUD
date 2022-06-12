package com.co.ias.birds.birds.application.domain.valueObjs;

import org.apache.commons.lang3.Validate;

public class BirdId {
    private final Long value;

    public BirdId(Long value) {
        Validate.notNull(value, "Bird id can not be null");
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "BirdId{" +
                "id=" + value +
                '}';
    }
}
