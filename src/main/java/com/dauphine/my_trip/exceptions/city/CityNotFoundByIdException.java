package com.dauphine.my_trip.exceptions.city;

import java.util.UUID;

public class CityNotFoundByIdException extends Exception {
    public CityNotFoundByIdException(UUID cityId) {
        super("The city designated by id " + cityId + " was not found.");
    }
}
