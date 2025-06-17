package com.dauphine.my_trip.services;

import com.dauphine.my_trip.exceptions.activity.ActivityNameAlreadyExistsException;
import com.dauphine.my_trip.exceptions.activity.ActivityNotFoundByIdException;
import com.dauphine.my_trip.models.Activity;
import com.dauphine.my_trip.models.City;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ActivityService {

    List<Activity> getAllActivities();

    Activity getActivityById(UUID activityId) throws ActivityNotFoundByIdException;

    Optional<Activity> getActivityByName(String activityName);

    List<Activity> getActivitiesByNameIgnoreCase(String activityName);

    Activity createActivity(String newActivityName, String newActivityDescription, int newActivityDuration,
                            int newActivityPrice, City newActivityCity) throws ActivityNameAlreadyExistsException;

    Activity updateActivity(UUID activityId, String newActivityName, String newActivityDescription, int newActivityDuration,
                            int newActivityPrice, City newActivityCity)
            throws ActivityNotFoundByIdException, ActivityNameAlreadyExistsException;

    void deleteActivity(UUID activityToDeleteId) throws ActivityNotFoundByIdException;
}
