package com.dauphine.my_trip.exceptions.pointOfInterest;

public class PointOfInterestNameAlreadyExistsException extends Exception {
    public PointOfInterestNameAlreadyExistsException(String pointOfInterestName) {
        super("The point of interest " + pointOfInterestName + " already exists.");
    }
}
