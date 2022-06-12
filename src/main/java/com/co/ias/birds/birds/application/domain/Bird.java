package com.co.ias.birds.birds.application.domain;

import com.co.ias.birds.birds.application.domain.valueObjs.*;

public class Bird {
    private final BirdId birdId;
    private final BirdCommonName birdCommonName;
    private final BirdScientificName birdScientificName;
    private final ZoneName zoneName;
    private final ConfirmedQuantity confirmedQuantity;

    public Bird(BirdId birdId, BirdCommonName birdCommonName, BirdScientificName birdScientificName, ZoneName zoneName, ConfirmedQuantity confirmedQuantity) {
        this.birdId = birdId;
        this.birdCommonName = birdCommonName;
        this.birdScientificName = birdScientificName;
        this.zoneName = zoneName;
        this.confirmedQuantity = confirmedQuantity;
    }

    public BirdId getBirdId() {
        return birdId;
    }

    public BirdCommonName getBirdCommonName() {
        return birdCommonName;
    }

    public BirdScientificName getBirdScientificName() {
        return birdScientificName;
    }

    public ZoneName getZoneName() {
        return zoneName;
    }

    public ConfirmedQuantity getConfirmedQuantity() {
        return confirmedQuantity;
    }

    @Override
    public String toString() {
        return "Bird{" +
                "birdId=" + birdId +
                ", birdCommonName=" + birdCommonName +
                ", birdScientificName=" + birdScientificName +
                ", zoneName=" + zoneName +
                ", confirmedQuantity=" + confirmedQuantity +
                '}';
    }
}
