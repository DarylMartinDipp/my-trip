package com.dauphine.my_trip.services.impl;

import com.dauphine.my_trip.exceptions.accommodation.AccommodationNameAlreadyExistsException;
import com.dauphine.my_trip.exceptions.accommodation.AccommodationNotFoundByIdException;
import com.dauphine.my_trip.models.Accommodation;
import com.dauphine.my_trip.models.City;
import com.dauphine.my_trip.repositories.AccommodationRepository;
import com.dauphine.my_trip.services.AccommodationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository) {
        this.accommodationRepository = accommodationRepository;
    }

    @Override
    public List<Accommodation> getAllAccommodations() {
        return accommodationRepository.findAll();
    }

    @Override
    public Accommodation getAccommodationById(UUID accommodationId) throws AccommodationNotFoundByIdException {
        return accommodationRepository.findById(accommodationId).orElseThrow(() ->
                new AccommodationNotFoundByIdException(accommodationId));
    }

    @Override
    public Optional<Accommodation> getAccommodationByName(String accommodationName) {
        return accommodationRepository.findByName(accommodationName);
    }

    @Override
    public List<Accommodation> getAccommodationByNameIgnoreCase(String accommodationName) {
        return accommodationRepository.findByNameContainingIgnoreCase(accommodationName);
    }

    @Override
    public List<Accommodation> getAccommodationsByCity(UUID cityId) {
        return accommodationRepository.findByCity(cityId);
    }

    @Override
    public Accommodation createAccommodation(String newAccommodationName, String newAccommodationType,
                                             int newAccommodationRating, int newAccommodationPrice, String newAccommodationAddress,
                                             City newAccommodationCity) throws AccommodationNameAlreadyExistsException {
        Optional<Accommodation> existingAccommodationByName = getAccommodationByName(newAccommodationName);
        if (existingAccommodationByName.isPresent()) throw new AccommodationNameAlreadyExistsException(newAccommodationName);

        Accommodation newAccommodation = new Accommodation(newAccommodationName, newAccommodationType,
                newAccommodationRating,newAccommodationPrice, newAccommodationAddress, newAccommodationCity);
        return accommodationRepository.save(newAccommodation);
    }

    @Override
    public Accommodation updateAccommodation(UUID accommodationId, String newAccommodationName,String newAccommodationType,
                                             int newAccommodationRating, int newAccommodationPrice,
                                             String newAccommodationAddress, City newAccommodationCity)
            throws AccommodationNotFoundByIdException, AccommodationNameAlreadyExistsException {
        Accommodation accommodationToUpdate = getAccommodationById(accommodationId);

        Optional<Accommodation> existingAccommodationByName = getAccommodationByName(newAccommodationName);
        if (existingAccommodationByName.isPresent() && !existingAccommodationByName.get().equals(accommodationToUpdate))
            throw new AccommodationNameAlreadyExistsException(newAccommodationName);

        accommodationToUpdate.setName(newAccommodationName);
        accommodationToUpdate.setType(newAccommodationType);
        accommodationToUpdate.setRating(newAccommodationRating);
        accommodationToUpdate.setPrice(newAccommodationPrice);
        accommodationToUpdate.setAddress(newAccommodationAddress);
        accommodationToUpdate.setCity(newAccommodationCity);

        return accommodationRepository.save(accommodationToUpdate);
    }

    @Override
    public void deleteAccommodation(UUID accommodationToDeleteId) throws AccommodationNotFoundByIdException {
        getAccommodationById(accommodationToDeleteId);
        accommodationRepository.deleteById(accommodationToDeleteId);
    }
}
