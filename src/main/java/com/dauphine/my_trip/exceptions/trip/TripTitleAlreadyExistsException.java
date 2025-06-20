package com.dauphine.my_trip.exceptions.trip;

public class TripTitleAlreadyExistsException extends Exception {
    public TripTitleAlreadyExistsException(String tripTitle) {
        super("The point of interest " + tripTitle + " already exists.");
    }
}
