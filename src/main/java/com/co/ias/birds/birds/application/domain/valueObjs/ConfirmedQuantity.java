package com.co.ias.birds.birds.application.domain.valueObjs;

import org.apache.commons.lang3.Validate;

public class ConfirmedQuantity {
    private final Integer value;

    public ConfirmedQuantity(Integer value) {
        System.out.println(value);
        if (value != null) {
            if (value != 0) {
                Validate.isTrue(value > 0 && value <= 100000, "The confirmed quantity of birds should be between 1 and 100.000");
            }
        }
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ConfirmedQuantity{" +
                "value=" + value +
                '}';
    }
}
