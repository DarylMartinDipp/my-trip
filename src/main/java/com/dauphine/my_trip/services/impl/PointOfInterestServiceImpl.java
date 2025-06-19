package com.dauphine.my_trip.services.impl;

import com.dauphine.my_trip.exceptions.pointOfInterest.PointOfInterestNameAlreadyExistsException;
import com.dauphine.my_trip.exceptions.pointOfInterest.PointOfInterestNotFoundByIdException;
import com.dauphine.my_trip.models.City;
import com.dauphine.my_trip.models.PointOfInterest;
import com.dauphine.my_trip.repositories.PointOfInterestRepository;
import com.dauphine.my_trip.services.PointOfInterestService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PointOfInterestServiceImpl implements PointOfInterestService {
    private final PointOfInterestRepository pointOfInterestRepository;

    public PointOfInterestServiceImpl(PointOfInterestRepository pointOfInterestRepository) {
        this.pointOfInterestRepository = pointOfInterestRepository;
    }

    @Override
    public List<PointOfInterest> getAllPointsOfInterest() {
        return pointOfInterestRepository.findAll();
    }

    @Override
    public PointOfInterest getPointOfInterestById(UUID pointOfInterestId) throws PointOfInterestNotFoundByIdException {
        return pointOfInterestRepository.findById(pointOfInterestId).orElseThrow(() ->
                new PointOfInterestNotFoundByIdException(pointOfInterestId));
    }

    @Override
    public Optional<PointOfInterest> getPointOfInterestByName(String pointOfInterestName) {
        return pointOfInterestRepository.findByName(pointOfInterestName);
    }

    @Override
    public List<PointOfInterest> getPointsOfInterestByNameIgnoreCase(String pointOfInterestName) {
        return pointOfInterestRepository.findByNameContainingIgnoreCase(pointOfInterestName);
    }

    @Override
    public List<PointOfInterest> getPointsOfInterestByCity(UUID cityId) {
        return pointOfInterestRepository.findByCity(cityId);
    }

    @Override
    public PointOfInterest createPointOfInterest(String newPointOfInterestName, String newPointOfInterestDescription,
                                                 City newPointOfInterestCity) throws PointOfInterestNameAlreadyExistsException {
        Optional<PointOfInterest> existingPointOfInterestByName = getPointOfInterestByName(newPointOfInterestName);
        if (existingPointOfInterestByName.isPresent()) throw new PointOfInterestNameAlreadyExistsException(newPointOfInterestName);

        PointOfInterest newPointOfInterest = new PointOfInterest(newPointOfInterestName, newPointOfInterestDescription,
                newPointOfInterestCity);
        return pointOfInterestRepository.save(newPointOfInterest);
    }

    @Override
    public PointOfInterest updatePointOfInterest(UUID pointOfInterestId, String newPointOfInterestName,
                                                 String newPointOfInterestDescription, City newPointOfInterestCity)
            throws PointOfInterestNotFoundByIdException, PointOfInterestNameAlreadyExistsException {
        PointOfInterest pointOfInterestToUpdate = getPointOfInterestById(pointOfInterestId);

        Optional<PointOfInterest> existingPointOfInterestByName = getPointOfInterestByName(newPointOfInterestName);
        if (existingPointOfInterestByName.isPresent() && !existingPointOfInterestByName.get().equals(pointOfInterestToUpdate))
            throw new PointOfInterestNameAlreadyExistsException(newPointOfInterestName);

        pointOfInterestToUpdate.setName(newPointOfInterestName);
        pointOfInterestToUpdate.setDescription(newPointOfInterestDescription);
        pointOfInterestToUpdate.setCity(newPointOfInterestCity);

        return pointOfInterestRepository.save(pointOfInterestToUpdate);
    }

    @Override
    public void deletePointOfInterest(UUID pointOfInterestToDeleteId) throws PointOfInterestNotFoundByIdException {
        getPointOfInterestById(pointOfInterestToDeleteId);
        pointOfInterestRepository.deleteById(pointOfInterestToDeleteId);
    }
}
