package com.dauphine.my_trip.dto;

import com.dauphine.my_trip.models.City;

public class CreatePointOfInterestRequest {
    private String name;
    private String description;
    private City city;

    public CreatePointOfInterestRequest(String name, String description, City city) {
        this.name = name;
        this.description = description;
        this.city = city;
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
