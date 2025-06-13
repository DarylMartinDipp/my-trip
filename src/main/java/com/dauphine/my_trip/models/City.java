package com.dauphine.my_trip.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class City {
    @Id
    @Column
    private UUID id;

    @Column
    private String name;

    @Column
    private String country;

    public City() {}

    public City(String name, String country) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.country = country;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
