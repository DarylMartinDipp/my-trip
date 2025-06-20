package com.dauphine.my_trip.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "step")
public class Step {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "day")
    private int day;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @ManyToMany
    @JoinTable(
            name = "step_activity",
            joinColumns = @JoinColumn(name = "step_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id")
    )
    private List<Activity> activities = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "step_poi",
            joinColumns = @JoinColumn(name = "step_id"),
            inverseJoinColumns = @JoinColumn(name = "poi_id")
    )
    private List<PointOfInterest> pointsOfInterest = new ArrayList<>();

    public Step() {}

    public Step(int day, City city, Accommodation accommodation, Trip trip) {
        this.id = UUID.randomUUID();
        this.day = day;
        this.city = city;
        this.accommodation = accommodation;
        this.trip = trip;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public List<PointOfInterest> getPointOfInterests() {
        return pointsOfInterest;
    }

    public void setPointOfInterests(List<PointOfInterest> pointOfInterests) {
        this.pointsOfInterest = pointOfInterests;
    }
}
