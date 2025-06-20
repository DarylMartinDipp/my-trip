package com.dauphine.my_trip.controllers;

import com.dauphine.my_trip.dto.CreateTripRequest;
import com.dauphine.my_trip.exceptions.trip.TripNotFoundByIdException;
import com.dauphine.my_trip.exceptions.trip.TripTitleAlreadyExistsException;
import com.dauphine.my_trip.models.Trip;
import com.dauphine.my_trip.services.TripService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/trips")
@Tag(
        name = "Trip controller API",
        description = "Trip-related endpoints"
)
public class TripController {
    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping
    @Operation(
            summary = "Get all trips endpoint",
            description = "Return all trips that are in the database, sorted " +
                    "by alphabetical order."
    )
    public ResponseEntity<List<Trip>> getAllTrips(@RequestParam (required = false) String tripTitle) {
        List<Trip> tripsToGet = tripTitle == null || tripTitle.isBlank() ?
                tripService.getAllTrips() : tripService.getTripByTitleIgnoreCase(tripTitle);
        tripsToGet.sort(Comparator.comparing(Trip::getTitle));
        return ResponseEntity.ok(tripsToGet);
    }

    @GetMapping("/{tripId}")
    @Operation(
            summary = "Get a trip by ID endpoint",
            description = "Return a certain trip according to its id."
    )
    public ResponseEntity<Trip> getTripById(@PathVariable UUID tripId) {
        try {
            final Trip tripToGet = tripService.getTripById(tripId);
            return ResponseEntity.ok(tripToGet);
        } catch (TripNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(
            summary = "Create a new trip endpoint",
            description = "Create a new trip with all its data."
    )
    public ResponseEntity<Trip> createTrip(@RequestBody CreateTripRequest tripToCreate) {
        try {
            Trip trip = tripService.createTrip(
                    tripToCreate.getTitle(), tripToCreate.getStartDate(), tripToCreate.getStartDate()
            );
            return ResponseEntity
                    .created(URI.create("trips/" + trip.getId()))
                    .body(trip);
        } catch (TripTitleAlreadyExistsException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{tripId}")
    @Operation(
            summary = "Update a trip endpoint",
            description = "Update a trip according to the id."
    )
    public ResponseEntity<Trip> updateTrip(@PathVariable UUID tripId, @RequestBody CreateTripRequest tripToUpdate) {
        try {
            Trip trip = tripService.updateTrip(
                    tripId, tripToUpdate.getTitle(), tripToUpdate.getStartDate(), tripToUpdate.getEndDate()
            );
            return ResponseEntity.ok(trip);
        } catch (TripNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        } catch (TripTitleAlreadyExistsException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{tripId}")
    @Operation(
            summary = "Delete a trip endpoint",
            description = "Delete an existing trip according to the id."
    )
    public ResponseEntity<Void> deleteTrip(@PathVariable UUID tripId) {
        try {
            tripService.deleteTrip(tripId);
            return ResponseEntity.ok().build();
        } catch (TripNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
