package com.dauphine.my_trip.exceptions.activity;

import java.util.UUID;

public class ActivityNotFoundByIdException extends Exception {
    public ActivityNotFoundByIdException(UUID activityId) {
        super("The activity designated by id " + activityId + " was not found.");
    }
}
