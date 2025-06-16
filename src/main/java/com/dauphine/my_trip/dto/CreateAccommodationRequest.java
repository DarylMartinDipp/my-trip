package com.dauphine.my_trip.dto;

import com.dauphine.my_trip.models.City;

public class CreateAccommodationRequest {
    private String name;
    private String type;
    private int rating;
    private int price;
    private String address;
    private City city;

    public CreateAccommodationRequest(String name, String type, int rating, int price, String address, City city) {
        this.name = name;
        this.type = type;
        this.rating = rating;
        this.price = price;
        this.address = address;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
