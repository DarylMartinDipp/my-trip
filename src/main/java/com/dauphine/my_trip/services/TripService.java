package com.dauphine.my_trip.services;

import com.dauphine.my_trip.exceptions.trip.TripNotFoundByIdException;
import com.dauphine.my_trip.exceptions.trip.TripTitleAlreadyExistsException;
import com.dauphine.my_trip.models.Trip;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TripService {

    List<Trip> getAllTrips();

    Trip getTripById(UUID tripId) throws TripNotFoundByIdException;

    Optional<Trip> getTripByTitle(String tripTitle);

    List<Trip> getTripByTitleIgnoreCase(String tripTitle);

    Trip createTrip(String newTripTitle, LocalDate newStartDate, LocalDate newEndDate) throws TripTitleAlreadyExistsException;

    Trip updateTrip(UUID tripId, String newTripTitle, LocalDate newStartDate, LocalDate newEndDate)
            throws TripNotFoundByIdException, TripTitleAlreadyExistsException;

    void deleteTrip(UUID tripToDeleteId) throws TripNotFoundByIdException;
}
