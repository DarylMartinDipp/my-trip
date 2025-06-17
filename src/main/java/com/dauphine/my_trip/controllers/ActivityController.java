package com.dauphine.my_trip.controllers;

import com.dauphine.my_trip.dto.CreateActivityRequest;
import com.dauphine.my_trip.exceptions.activity.ActivityNameAlreadyExistsException;
import com.dauphine.my_trip.exceptions.activity.ActivityNotFoundByIdException;
import com.dauphine.my_trip.models.Activity;
import com.dauphine.my_trip.services.ActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/activities")
@Tag(
        name = "Activity controller API",
        description = "Activity-related endpoints"
)
public class ActivityController {
    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    @Operation(
            summary = "Get all activities endpoint",
            description = "Return all activities that are in the database, sorted " +
                    "by alphabetical order."
    )
    public ResponseEntity<List<Activity>> getAllActivities(@RequestParam (required = false) String activityName) {
        List<Activity> activitiesToGet = activityName == null || activityName.isBlank() ?
                activityService.getAllActivities() : activityService.getActivitiesByNameIgnoreCase(activityName);
        activitiesToGet.sort(Comparator.comparing(Activity::getName));
        return ResponseEntity.ok(activitiesToGet);
    }

    @GetMapping("/{activityId}")
    @Operation(
            summary = "Get an activity by ID endpoint",
            description = "Return a certain activity according to its id."
    )
    public ResponseEntity<Activity> getActivityById(@PathVariable UUID activityId) {
        try {
            final Activity activityToGet = activityService.getActivityById(activityId);
            return ResponseEntity.ok(activityToGet);
        } catch (ActivityNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-city")
    @Operation(
            summary = "Get activities by city endpoint",
            description = "Return all activities that are in a certain city, sorted" +
                    "by alphabetical order."
    )
    public ResponseEntity<List<Activity>> getActivitiesByCity(@RequestParam UUID cityId) {
        List<Activity> activitiesToGet = activityService.getActivitiesByCity(cityId);
        activitiesToGet.sort(Comparator.comparing(Activity::getName));
        return ResponseEntity.ok(activitiesToGet);
    }

    @PostMapping
    @Operation(
            summary = "Create a new activity endpoint",
            description = "Create a new activity with all its data."
    )
    public ResponseEntity<Activity> createActivity(@RequestBody CreateActivityRequest activityToCreate) {
        try {
            Activity activity = activityService.createActivity(
                    activityToCreate.getName(), activityToCreate.getDescription(), activityToCreate.getDuration(),
                    activityToCreate.getPrice(), activityToCreate.getCity()
            );
            return ResponseEntity
                    .created(URI.create("activities/" + activity.getId()))
                    .body(activity);
        } catch (ActivityNameAlreadyExistsException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{activityId}")
    @Operation(
            summary = "Update an activity endpoint",
            description = "Update an activity according to the id."
    )
    public ResponseEntity<Activity> updateActivity(@PathVariable UUID activityId,
                                                   @RequestBody CreateActivityRequest activityToUpdate) {
        try {
            Activity activity = activityService.updateActivity(
                    activityId, activityToUpdate.getName(), activityToUpdate.getDescription(),
                    activityToUpdate.getDuration(), activityToUpdate.getPrice(), activityToUpdate.getCity()
            );
            return ResponseEntity.ok(activity);
        } catch (ActivityNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        } catch (ActivityNameAlreadyExistsException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{activityId}")
    @Operation(
            summary = "Delete an activity endpoint",
            description = "Delete an existing activity according to the id."
    )
    public ResponseEntity<Void> deleteActivity(@PathVariable UUID activityId) {
        try {
            activityService.deleteActivity(activityId);
            return ResponseEntity.ok().build();
        } catch (ActivityNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
