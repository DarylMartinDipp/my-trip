package com.dauphine.my_trip.services.impl;

import com.dauphine.my_trip.exceptions.step.StepNotFoundByIdException;
import com.dauphine.my_trip.models.Accommodation;
import com.dauphine.my_trip.models.City;
import com.dauphine.my_trip.models.Step;
import com.dauphine.my_trip.models.Trip;
import com.dauphine.my_trip.repositories.StepRepository;
import com.dauphine.my_trip.services.StepService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StepServiceImpl implements StepService {
    private final StepRepository stepRepository;

    public StepServiceImpl(StepRepository stepRepository) {
        this.stepRepository = stepRepository;
    }

    @Override
    public List<Step> getAllSteps() {
        return stepRepository.findAll();
    }

    @Override
    public Step getStepById(UUID stepId) throws StepNotFoundByIdException {
        return stepRepository.findById(stepId).orElseThrow(() -> new StepNotFoundByIdException(stepId));
    }

    @Override
    public List<Step> getStepsByTripId(UUID tripId) {
        return stepRepository.findByTripId(tripId);
    }

    @Override
    public Step createStep(int newDay, City newCity, Accommodation newAccommodation, Trip newTrip) {
        Step newStep = new Step(newDay, newCity, newAccommodation, newTrip);
        return stepRepository.save(newStep);
    }

    @Override
    public Step updateStep(UUID stepId, int newDay, City newCity, Accommodation newAccommodation, Trip newTrip) throws StepNotFoundByIdException {
        Step stepToUpdate = getStepById(stepId);

        stepToUpdate.setDay(newDay);
        stepToUpdate.setCity(newCity);
        stepToUpdate.setAccommodation(newAccommodation);
        stepToUpdate.setTrip(newTrip);

        return stepRepository.save(stepToUpdate);
    }

    @Override
    public void deleteStep(UUID stepToDeleteId) throws StepNotFoundByIdException {
        getStepById(stepToDeleteId);
        stepRepository.deleteById(stepToDeleteId);
    }
}
