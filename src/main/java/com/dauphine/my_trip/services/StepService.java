package com.dauphine.my_trip.services;

import com.dauphine.my_trip.exceptions.step.StepNotFoundByIdException;
import com.dauphine.my_trip.models.Accommodation;
import com.dauphine.my_trip.models.City;
import com.dauphine.my_trip.models.Step;
import com.dauphine.my_trip.models.Trip;

import java.util.List;
import java.util.UUID;

public interface StepService {

    List<Step> getAllSteps();

    Step getStepById(UUID stepId) throws StepNotFoundByIdException;

    List<Step> getStepsByTripId(UUID tripId);

    Step createStep(int newDay, City newCity, Accommodation newAccommodation, Trip newTrip);

    Step updateStep(UUID stepId, int newDay, City newCity, Accommodation newAccommodation, Trip newTrip) throws StepNotFoundByIdException;

    void deleteStep(UUID stepToDeleteId) throws StepNotFoundByIdException;
}
