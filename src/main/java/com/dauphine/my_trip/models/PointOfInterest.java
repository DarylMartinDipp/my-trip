package com.dauphine.my_trip.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "pointofinterest")
public class PointOfInterest {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    public PointOfInterest() {}

    public PointOfInterest(String name, String description, City city) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;
        this.city = city;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
