package com.dauphine.my_trip.exceptions.step;

import java.util.UUID;

public class StepNotFoundByIdException extends Exception {
    public StepNotFoundByIdException(UUID stepId) {
      super("The step designated by id " + stepId + " was not found.");
    }
}
