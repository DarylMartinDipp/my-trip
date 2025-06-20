package com.dauphine.my_trip.controllers;

import com.dauphine.my_trip.dto.CreateStepRequest;
import com.dauphine.my_trip.exceptions.step.StepNotFoundByIdException;
import com.dauphine.my_trip.models.Step;
import com.dauphine.my_trip.services.StepService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/steps")
@Tag(
        name = "Step controller API",
        description = "Step-related endpoints"
)
public class StepController {
    private final StepService stepService;

    public StepController(StepService stepService) {
        this.stepService = stepService;
    }

    @GetMapping
    @Operation(
            summary = "Get all steps endpoint",
            description = "Return all steps that are in the database, sorted " +
                    "by trip, then by day."
    )
    public ResponseEntity<List<Step>> getAllSteps() {
        List<Step> stepsToGet = stepService.getAllSteps();

        stepsToGet.sort(
                Comparator.comparing((Step s) -> s.getTrip().getId())
                        .thenComparing(Step::getDay)
        );

        return ResponseEntity.ok(stepsToGet);
    }

    @GetMapping("/{stepId}")
    @Operation(
            summary = "Get a step by ID endpoint",
            description = "Return a certain step according to its id."
    )
    public ResponseEntity<Step> getStepById(@PathVariable UUID stepId) {
        try {
            final Step stepToGet = stepService.getStepById(stepId);
            return ResponseEntity.ok(stepToGet);
        } catch (StepNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-trip")
    @Operation(
            summary = "Get steps by trip endpoint",
            description = "Return all steps that are in a certain trip, sorted " +
                    "by day."
    )
    public ResponseEntity<List<Step>> getStepsByTrip(@RequestParam UUID tripId) {
        List<Step> stepsToGet = stepService.getStepsByTripId(tripId);
        stepsToGet.sort(Comparator.comparing(Step::getDay));
        return ResponseEntity.ok(stepsToGet);
    }

    @PostMapping("/{stepId}/activities")
    @Operation(
            summary = "Add activities to step",
            description = "Add a list of activities to a step, according to the " +
                    "activities IDs and the step ID."
    )
    public ResponseEntity<Void> addActivitiesToStep(@PathVariable UUID stepId, @RequestBody List<UUID> activityIds) {
        try {
            stepService.addActivitiesToStep(stepId, activityIds);
            return ResponseEntity.ok().build();
        } catch (StepNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{stepId}/activities")
    @Operation(
            summary = "Remove activities to step",
            description = "Remove a list of activities to a step, according to the " +
                    "activities IDs and the step ID."
    )
    public ResponseEntity<Void> removeActivitiesFromStep(@PathVariable UUID stepId, @RequestBody List<UUID> activityIds) {
        try {
            stepService.removeActivitiesFromStep(stepId, activityIds);
            return ResponseEntity.ok().build();
        } catch (StepNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(
            summary = "Create a new step endpoint",
            description = "Create a new step with all its data."
    )
    public ResponseEntity<Step> createStep(@RequestBody CreateStepRequest stepToCreate) {
        Step step = stepService.createStep(
                stepToCreate.getDay(), stepToCreate.getCity(), stepToCreate.getAccommodation(), stepToCreate.getTrip()
        );
        return ResponseEntity
                .created(URI.create("steps/" + step.getId()))
                .body(step);
    }

    @PutMapping("/{stepId}")
    @Operation(
            summary = "Update a step endpoint",
            description = "Update a step according to the id."
    )
    public ResponseEntity<Step> updateStep(@PathVariable UUID stepId, @RequestBody CreateStepRequest stepToUpdate) {
        try {
            Step step = stepService.updateStep(
                    stepId, stepToUpdate.getDay(), stepToUpdate.getCity(), stepToUpdate.getAccommodation(), stepToUpdate.getTrip()
            );
            return ResponseEntity.ok(step);
        } catch (StepNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{stepId}")
    @Operation(
            summary = "Delete a step endpoint",
            description = "Delete an existing step according to the id."
    )
    public ResponseEntity<Void> deleteStep(@PathVariable UUID stepId) {
        try {
            stepService.deleteStep(stepId);
            return ResponseEntity.ok().build();
        } catch (StepNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
