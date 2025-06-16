package com.dauphine.my_trip.controllers;

import com.dauphine.my_trip.dto.CreateAccommodationRequest;
import com.dauphine.my_trip.exceptions.accommodation.AccommodationNameAlreadyExistsException;
import com.dauphine.my_trip.exceptions.accommodation.AccommodationNotFoundByIdException;
import com.dauphine.my_trip.models.Accommodation;
import com.dauphine.my_trip.services.AccommodationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/accommodation")
@Tag(
        name = "Accommodation controller API",
        description = "Accommodation-related endpoints"
)
public class AccommodationController {
    private final AccommodationService accommodationService;

    public AccommodationController(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping
    @Operation(
            summary = "Get all the accommodations endpoint",
            description = "Return all accommodations that are in the database, sorted " +
                    "by alphabetical order."
    )
    public ResponseEntity<List<Accommodation>> getAllAccommodations(@RequestParam (required = false) String accommodationName) {
        List<Accommodation> accommodationsToGet = accommodationName == null || accommodationName.isBlank() ?
                accommodationService.getAllAccommodations() : accommodationService.getAccommodationByNameIgnoreCase(accommodationName);
        accommodationsToGet.sort(Comparator.comparing(Accommodation::getName));
        return ResponseEntity.ok(accommodationsToGet);
    }

    @GetMapping("/{accommodationId}")
    @Operation(
            summary = "Get an accommodation by ID endpoint",
            description = "Return a certain accommodation according to its id."
    )
    public ResponseEntity<Accommodation> getAccommodationById(@PathVariable UUID accommodationId) {
        try {
            final Accommodation accommodationToGet = accommodationService.getAccommodationById(accommodationId);
            return ResponseEntity.ok(accommodationToGet);
        } catch (AccommodationNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(
            summary = "Create a new accommodation endpoint",
            description = "Create a new accommodation with all its data."
    )
    public ResponseEntity<Accommodation> createAccommodation(@RequestBody CreateAccommodationRequest accommodationToCreate) {
        try {
            Accommodation accommodation = accommodationService.createAccommodation(
                    accommodationToCreate.getName(), accommodationToCreate.getType(), accommodationToCreate.getRating(), accommodationToCreate.getPrice(), accommodationToCreate.getAddress(), accommodationToCreate.getCity()
            );
            return ResponseEntity
                    .created(URI.create("accommodation/" + accommodation.getId()))
                    .body(accommodation);
        } catch (AccommodationNameAlreadyExistsException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{accommodationId}")
    @Operation(
            summary = "Update an accommodation endpoint",
            description = "Update an accommodation according to the id."
    )
    public ResponseEntity<Accommodation> updateAccommodation(@PathVariable UUID accommodationId, @RequestBody CreateAccommodationRequest accommodationToUpdate) {
        try {
            Accommodation accommodation = accommodationService.updateAccommodation(
                    accommodationId, accommodationToUpdate.getName(), accommodationToUpdate.getType(), accommodationToUpdate.getRating(), accommodationToUpdate.getPrice(), accommodationToUpdate.getAddress(), accommodationToUpdate.getCity()
            );
            return ResponseEntity.ok(accommodation);
        } catch (AccommodationNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        } catch (AccommodationNameAlreadyExistsException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{accommodationId}")
    @Operation(
            summary = "Delete an accommodation endpoint",
            description = "Delete an existing accommodation according to the id."
    )
    public ResponseEntity<Void> deleteAccommodation(@PathVariable UUID accommodationId) {
        try {
            accommodationService.deleteAccommodation(accommodationId);
            return ResponseEntity.ok().build();
        } catch (AccommodationNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
