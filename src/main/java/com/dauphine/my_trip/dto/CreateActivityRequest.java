package com.dauphine.my_trip.dto;

import com.dauphine.my_trip.models.City;

public class CreateActivityRequest {
    private String name;
    private String description;
    private int duration;
    private int price;
    private City city;

    public CreateActivityRequest(String name, String description, int duration, int price, City city) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.price = price;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
