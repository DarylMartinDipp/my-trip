package com.dauphine.my_trip.services;

import com.dauphine.my_trip.exceptions.accommodation.AccommodationNameAlreadyExistsException;
import com.dauphine.my_trip.exceptions.accommodation.AccommodationNotFoundByIdException;
import com.dauphine.my_trip.models.Accommodation;
import com.dauphine.my_trip.models.City;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccommodationService {

    List<Accommodation> getAllAccommodations();

    Accommodation getAccommodationById(UUID accommodationId) throws AccommodationNotFoundByIdException;

    Optional<Accommodation> getAccommodationByName(String accommodationName);

    List<Accommodation> getAccommodationByNameIgnoreCase(String accommodationName);

    Accommodation createAccommodation(String newAccommodationName, String newAccommodationType, int newAccommodationRating,
                                      int newAccommodationPrice, String newAccommodationAddress, City newAccommodationCity)
            throws AccommodationNameAlreadyExistsException;

    Accommodation updateAccommodation(UUID accommodationId, String newAccommodationName, String newAccommodationType,
                                      int newAccommodationRating, int newAccommodationPrice,
                                      String newAccommodationAddress,City newAccommodationCity)
            throws AccommodationNotFoundByIdException, AccommodationNameAlreadyExistsException;

    void deleteAccommodation(UUID accommodationToDeleteId) throws AccommodationNotFoundByIdException;
}
