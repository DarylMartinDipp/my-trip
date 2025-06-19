package com.dauphine.my_trip.services.impl;

import com.dauphine.my_trip.exceptions.activity.ActivityNameAlreadyExistsException;
import com.dauphine.my_trip.exceptions.activity.ActivityNotFoundByIdException;
import com.dauphine.my_trip.models.Activity;
import com.dauphine.my_trip.models.City;
import com.dauphine.my_trip.repositories.ActivityRepository;
import com.dauphine.my_trip.services.ActivityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ActivityServiceImpl implements ActivityService {
    private final ActivityRepository activityRepository;

    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    @Override
    public Activity getActivityById(UUID activityId) throws ActivityNotFoundByIdException {
        return activityRepository.findById(activityId).orElseThrow(() ->
                new ActivityNotFoundByIdException(activityId));
    }

    @Override
    public Optional<Activity> getActivityByName(String activityName) {
        return activityRepository.findByName(activityName);
    }

    @Override
    public List<Activity> getActivitiesByNameIgnoreCase(String activityName) {
        return activityRepository.findByNameContainingIgnoreCase(activityName);
    }

    public List<Activity> getActivitiesByCity(UUID cityId) {
        return activityRepository.findByCity(cityId);
    }

    @Override
    public Activity createActivity(String newActivityName, String newActivityDescription, int newActivityDuration,
                                   int newActivityPrice, City newActivityCity) throws ActivityNameAlreadyExistsException {
        Optional<Activity> existingActivityByName = getActivityByName(newActivityName);
        if (existingActivityByName.isPresent()) throw new ActivityNameAlreadyExistsException(newActivityName);

        Activity newActivity = new Activity(newActivityName, newActivityDescription, newActivityDuration,
                newActivityPrice, newActivityCity);
        return activityRepository.save(newActivity);
    }

    @Override
    public Activity updateActivity(UUID activityId, String newActivityName, String newActivityDescription,
                                   int newActivityDuration, int newActivityPrice, City newActivityCity)
            throws ActivityNotFoundByIdException, ActivityNameAlreadyExistsException {
        Activity activityToUpdate = getActivityById(activityId);

        Optional<Activity> existingActivityByName = getActivityByName(newActivityName);
        if (existingActivityByName.isPresent() && !existingActivityByName.get().equals(activityToUpdate))
            throw new ActivityNameAlreadyExistsException(newActivityName);

        activityToUpdate.setName(newActivityName);
        activityToUpdate.setDescription(newActivityDescription);
        activityToUpdate.setDuration(newActivityDuration);
        activityToUpdate.setPrice(newActivityPrice);
        activityToUpdate.setCity(newActivityCity);

        return activityRepository.save(activityToUpdate);
    }

    @Override
    public void deleteActivity(UUID activityToDeleteId) throws ActivityNotFoundByIdException {
        getActivityById(activityToDeleteId);
        activityRepository.deleteById(activityToDeleteId);
    }
}
