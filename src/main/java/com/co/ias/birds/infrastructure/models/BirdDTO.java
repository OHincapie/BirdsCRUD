package com.co.ias.birds.infrastructure.models;

import com.co.ias.birds.birds.application.domain.Bird;
import com.co.ias.birds.birds.application.domain.valueObjs.*;

public class BirdDTO {
    private Long id;
    private String common_name;
    private String scientific_name;
    private String zone_name;
    private Integer confirmed_quantity;

    private String status;

    public BirdDTO(Long id, String common_name, String scientific_name, String zone_name, Integer confirmed_quantity) {
        this.id = id;
        this.common_name = common_name;
        this.scientific_name = scientific_name;
        this.zone_name = zone_name;
        this.confirmed_quantity = confirmed_quantity;
    }

    public BirdDTO() {
    }

    public Bird toDomain() {
        return new Bird(
                new BirdId(id),
                new BirdCommonName(common_name),
                new BirdScientificName(scientific_name),
                new ZoneName(zone_name),
                new ConfirmedQuantity(confirmed_quantity)
        );
    }

    public static BirdDTO fromDomain(Bird bird) {
        BirdDTO birdDTO = new BirdDTO();
        birdDTO.setId(bird.getBirdId().getValue());
        birdDTO.setCommon_name(bird.getBirdCommonName().getValue());
        birdDTO.setScientific_name(bird.getBirdScientificName().getValue());
        birdDTO.setZone_name(bird.getZoneName().getValue());
        birdDTO.setConfirmed_quantity(bird.getConfirmedQuantity().getValue());
        return birdDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommon_name() {
        return common_name;
    }

    public void setCommon_name(String common_name) {
        this.common_name = common_name;
    }

    public String getScientific_name() {
        return scientific_name;
    }

    public void setScientific_name(String scientific_name) {
        this.scientific_name = scientific_name;
    }

    public String getZone_name() {
        return zone_name;
    }

    public void setZone_name(String zone_name) {
        this.zone_name = zone_name;
    }

    public Integer getConfirmed_quantity() {
        return confirmed_quantity;
    }

    public void setConfirmed_quantity(Integer confirmed_quantity) {
        this.confirmed_quantity = confirmed_quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BirdsDTO{" +
                "id=" + id +
                ", commonName='" + common_name + '\'' +
                ", scientificName='" + scientific_name + '\'' +
                ", zoneName='" + zone_name + '\'' +
                ", confirmedQuantity=" + confirmed_quantity +
                '}';
    }
}
