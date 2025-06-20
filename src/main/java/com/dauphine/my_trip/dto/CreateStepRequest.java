package com.dauphine.my_trip.dto;

import com.dauphine.my_trip.models.Accommodation;
import com.dauphine.my_trip.models.City;
import com.dauphine.my_trip.models.Trip;

public class CreateStepRequest {
    private int day;
    private City city;
    private Accommodation accommodation;
    private Trip trip;

    public CreateStepRequest(int day, City city, Accommodation accommodation, Trip trip) {
        this.day = day;
        this.city = city;
        this.accommodation = accommodation;
        this.trip = trip;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Accommodation accommodation) {
        this.accommodation = accommodation;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
