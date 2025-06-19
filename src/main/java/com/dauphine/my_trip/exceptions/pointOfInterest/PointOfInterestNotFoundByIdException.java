package com.dauphine.my_trip.exceptions.pointOfInterest;

import java.util.UUID;

public class PointOfInterestNotFoundByIdException extends Exception {
    public PointOfInterestNotFoundByIdException(UUID pointOfInterestId) {
        super("The point of interest designated by id " + pointOfInterestId + " was not found.");
    }
}
