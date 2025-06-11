package com.dauphine.my_trip.exceptions.city;

public class CityNameAlreadyExistsException extends Exception {
    public CityNameAlreadyExistsException(String cityName) {
        super("The city " + cityName + " already exists.");
    }
}
