package com.dauphine.my_trip.exceptions.activity;

public class ActivityNameAlreadyExistsException extends Exception {
    public ActivityNameAlreadyExistsException(String activityName) {
        super("The activity " + activityName + " already exists.");
    }
}
