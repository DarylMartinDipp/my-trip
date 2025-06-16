package com.dauphine.my_trip.exceptions.accommodation;

public class AccommodationNameAlreadyExistsException extends Exception {
    public AccommodationNameAlreadyExistsException(String accommodationName) {
        super("The accommodation " + accommodationName + " already exists.");
    }
}
