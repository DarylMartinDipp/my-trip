package com.dauphine.my_trip.exceptions.trip;

import java.util.UUID;

public class TripNotFoundByIdException extends Exception {
    public TripNotFoundByIdException(UUID tripId) {
        super("The trip designated by id " + tripId + " was not found.");
    }
}
