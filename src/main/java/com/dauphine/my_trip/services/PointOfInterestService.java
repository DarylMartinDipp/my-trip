package com.dauphine.my_trip.services;

import com.dauphine.my_trip.exceptions.pointOfInterest.PointOfInterestNameAlreadyExistsException;
import com.dauphine.my_trip.exceptions.pointOfInterest.PointOfInterestNotFoundByIdException;
import com.dauphine.my_trip.models.City;
import com.dauphine.my_trip.models.PointOfInterest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PointOfInterestService {

    List<PointOfInterest> getAllPointsOfInterest();

    PointOfInterest getPointOfInterestById(UUID pointOfInterestId) throws PointOfInterestNotFoundByIdException;

    Optional<PointOfInterest> getPointOfInterestByName(String pointOfInterestName);

    List<PointOfInterest> getPointsOfInterestByNameIgnoreCase(String pointOfInterestName);

    List<PointOfInterest> getPointsOfInterestByCity(UUID cityId);

    PointOfInterest createPointOfInterest(String newPointOfInterestName, String newPointOfInterestDescription,
                                          City newPointOfInterestCity) throws PointOfInterestNameAlreadyExistsException;

    PointOfInterest updatePointOfInterest(UUID pointOfInterestId, String newPointOfInterestName,
                                          String newPointOfInterestDescription, City newPointOfInterestCity)
            throws PointOfInterestNotFoundByIdException, PointOfInterestNameAlreadyExistsException;

    void deletePointOfInterest(UUID pointOfInterestToDeleteId) throws PointOfInterestNotFoundByIdException;
}
