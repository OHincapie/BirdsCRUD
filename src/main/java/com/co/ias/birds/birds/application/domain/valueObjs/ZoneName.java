package com.co.ias.birds.birds.application.domain.valueObjs;

import org.apache.commons.lang3.Validate;

public class ZoneName {
    private final String value;

    public ZoneName(String value) {
        Validate.notNull(value, "Bird's zone name can not be null");
        Validate.isTrue(value.length() <= 20, "Bird's zone name can not be longer than 20 characters");
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "BirdCommonName{" +
                "value='" + value + '\'' +
                '}';
    }
}
