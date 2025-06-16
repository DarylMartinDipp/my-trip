package com.dauphine.my_trip.exceptions.accommodation;

import java.util.UUID;

public class AccommodationNotFoundByIdException extends Exception {
    public AccommodationNotFoundByIdException(UUID accommodationId) {
        super("The accommodation designated by id " + accommodationId + " was not found.");
    }
}
