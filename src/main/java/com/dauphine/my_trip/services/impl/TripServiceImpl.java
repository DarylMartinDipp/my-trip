package com.dauphine.my_trip.services.impl;

import com.dauphine.my_trip.exceptions.trip.TripNotFoundByIdException;
import com.dauphine.my_trip.exceptions.trip.TripTitleAlreadyExistsException;
import com.dauphine.my_trip.models.Trip;
import com.dauphine.my_trip.repositories.TripRepository;
import com.dauphine.my_trip.services.TripService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;

    public TripServiceImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    @Override
    public Trip getTripById(UUID tripId) throws TripNotFoundByIdException {
        return tripRepository.findById(tripId).orElseThrow(() -> new TripNotFoundByIdException(tripId));
    }

    @Override
    public Optional<Trip> getTripByTitle(String tripTitle) {
        return tripRepository.findByTitle(tripTitle);
    }

    @Override
    public List<Trip> getTripByTitleIgnoreCase(String tripTitle) {
        return tripRepository.findByTitleContainingIgnoreCase(tripTitle);
    }

    @Override
    public Trip createTrip(String newTripTitle, LocalDate newStartDate, LocalDate newEndDate) throws TripTitleAlreadyExistsException {
        Optional<Trip> existingTripByName = getTripByTitle(newTripTitle);
        if (existingTripByName.isPresent()) throw new TripTitleAlreadyExistsException(newTripTitle);

        Trip newTrip = new Trip(newTripTitle, newStartDate, newEndDate);
        return tripRepository.save(newTrip);
    }

    @Override
    public Trip updateTrip(UUID tripId, String newTripTitle, LocalDate newStartDate, LocalDate newEndDate)
            throws TripNotFoundByIdException, TripTitleAlreadyExistsException {
        Trip tripToUpdate = getTripById(tripId);

        Optional<Trip> existingTripByName = getTripByTitle(newTripTitle);
        if (existingTripByName.isPresent() && !existingTripByName.get().equals(tripToUpdate))
            throw new TripTitleAlreadyExistsException(newTripTitle);

        tripToUpdate.setTitle(newTripTitle);
        tripToUpdate.setStartdate(newStartDate);
        tripToUpdate.setEnddate(newEndDate);

        return tripRepository.save(tripToUpdate);
    }

    @Override
    public void deleteTrip(UUID tripToDeleteId) throws TripNotFoundByIdException {
        getTripById(tripToDeleteId);
        tripRepository.deleteById(tripToDeleteId);
    }
}
