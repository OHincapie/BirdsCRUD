package com.co.ias.birds.infrastructure.models;

import com.co.ias.birds.birds.application.domain.Bird;
import com.co.ias.birds.birds.application.domain.valueObjs.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BirdDAO {
    private Long id;
    private String common_name;
    private String scientific_name;
    private String zone_name;
    private Integer confirmed_quantity;

    public BirdDAO(Long id, String common_name, String scientific_name, String zone_name, Integer confirmed_quantity) {
        this.id = id;
        this.common_name = common_name;
        this.scientific_name = scientific_name;
        this.zone_name = zone_name;
        this.confirmed_quantity = confirmed_quantity;
    }

    public BirdDAO() {
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

    public static BirdDAO fromDomain(Bird bird) {
        BirdDAO birdDAO = new BirdDAO();
        birdDAO.setId(bird.getBirdId().getValue());
        birdDAO.setCommon_name(bird.getBirdCommonName().getValue());
        birdDAO.setScientific_name(bird.getBirdScientificName().getValue());
        birdDAO.setZone_name(bird.getZoneName().getValue());
        birdDAO.setConfirmed_quantity(bird.getConfirmedQuantity().getValue());
        return birdDAO;
    }

    public static BirdDAO fromResultSet(ResultSet resultSet) throws SQLException {
        BirdDAO birdDAO = new BirdDAO();
        birdDAO.setId(resultSet.getLong("id"));
        birdDAO.setCommon_name(resultSet.getString("common_name"));
        birdDAO.setScientific_name(resultSet.getString("scientific_name"));
        birdDAO.setZone_name(resultSet.getString("zone_name"));
        birdDAO.setConfirmed_quantity(resultSet.getInt("confirmed_quantity"));
        return birdDAO;
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

    @Override
    public String toString() {
        return "BirdDAO{" +
                "id=" + id +
                ", commonName='" + common_name + '\'' +
                ", scientificName='" + scientific_name + '\'' +
                ", zoneName='" + zone_name + '\'' +
                ", confirmedQuantity=" + confirmed_quantity +
                '}';
    }
}
