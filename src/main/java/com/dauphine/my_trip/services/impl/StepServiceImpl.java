package com.dauphine.my_trip.services.impl;

import com.dauphine.my_trip.exceptions.step.StepNotFoundByIdException;
import com.dauphine.my_trip.models.*;
import com.dauphine.my_trip.repositories.ActivityRepository;
import com.dauphine.my_trip.repositories.StepRepository;
import com.dauphine.my_trip.services.StepService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StepServiceImpl implements StepService {
    private final StepRepository stepRepository;
    private final ActivityRepository activityRepository;

    public StepServiceImpl(StepRepository stepRepository, ActivityRepository activityRepository) {
        this.stepRepository = stepRepository;
        this.activityRepository = activityRepository;
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
    public void addActivitiesToStep(UUID stepId, List<UUID> activityIds) throws StepNotFoundByIdException {
        Step step = getStepById(stepId);
        List<Activity> activitiesToAdd = activityRepository.findAllById(activityIds);
        step.getActivities().addAll(activitiesToAdd);

        stepRepository.save(step);
    }

    @Override
    public void removeActivitiesFromStep(UUID stepId, List<UUID> activityIds) throws StepNotFoundByIdException {
        Step step = getStepById(stepId);
        List<Activity> activitiesToRemove = activityRepository.findAllById(activityIds);
        step.getActivities().removeAll(activitiesToRemove);

        stepRepository.save(step);
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
