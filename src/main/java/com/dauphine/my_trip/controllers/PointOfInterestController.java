package com.dauphine.my_trip.controllers;

import com.dauphine.my_trip.dto.CreatePointOfInterestRequest;
import com.dauphine.my_trip.exceptions.pointOfInterest.PointOfInterestNameAlreadyExistsException;
import com.dauphine.my_trip.exceptions.pointOfInterest.PointOfInterestNotFoundByIdException;
import com.dauphine.my_trip.models.PointOfInterest;
import com.dauphine.my_trip.services.PointOfInterestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pointsOfInterest")
@Tag(
        name = "Point of interest controller API",
        description = "Point of interest-related endpoints"
)
public class PointOfInterestController {
    private final PointOfInterestService pointOfInterestService;

    public PointOfInterestController(PointOfInterestService pointOfInterestService) {
        this.pointOfInterestService = pointOfInterestService;
    }

    @GetMapping
    @Operation(
            summary = "Get all points of interest endpoint",
            description = "Return all points of interest that are in the database, sorted " +
                    "by alphabetical order."
    )
    public ResponseEntity<List<PointOfInterest>> getAllPointsOfInterest(@RequestParam (required = false) String pointOfInterestName) {
        List<PointOfInterest> pointsOfInterestToGet = pointOfInterestName == null || pointOfInterestName.isBlank() ?
                pointOfInterestService.getAllPointsOfInterest() :
                pointOfInterestService.getPointsOfInterestByNameIgnoreCase(pointOfInterestName);
        pointsOfInterestToGet.sort(Comparator.comparing(PointOfInterest::getName));
        return ResponseEntity.ok(pointsOfInterestToGet);
    }

    @GetMapping("/{pointOfInterestId}")
    @Operation(
            summary = "Get a point of interest by ID endpoint",
            description = "Return a certain point of interest according to its id."
    )
    public ResponseEntity<PointOfInterest> getPointOfInterestById(@PathVariable UUID pointOfInterestId) {
        try {
            final PointOfInterest pointOfInterestToGet = pointOfInterestService.getPointOfInterestById(pointOfInterestId);
            return ResponseEntity.ok(pointOfInterestToGet);
        } catch (PointOfInterestNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-city")
    @Operation(
            summary = "Get points of interest by city endpoint",
            description = "Return all points of interest that are in a certain city, sorted" +
                    "by alphabetical order."
    )
    public ResponseEntity<List<PointOfInterest>> getPointOfInterestByCity(@RequestParam UUID cityId) {
        List<PointOfInterest> pointsOfInterestToGet = pointOfInterestService.getPointsOfInterestByCity(cityId);
        pointsOfInterestToGet.sort(Comparator.comparing(PointOfInterest::getName));
        return ResponseEntity.ok(pointsOfInterestToGet);
    }

    @PostMapping
    @Operation(
            summary = "Create a new point of interest endpoint",
            description = "Create a new point of interest with all its data."
    )
    public ResponseEntity<PointOfInterest> createPointOfInterest(@RequestBody CreatePointOfInterestRequest pointOfInterestToCreate) {
        try {
            PointOfInterest pointOfInterest = pointOfInterestService.createPointOfInterest(
                    pointOfInterestToCreate.getName(), pointOfInterestToCreate.getDescription(),
                    pointOfInterestToCreate.getCity()
            );
            return ResponseEntity
                    .created(URI.create("pointsOfInterest/" + pointOfInterest.getId()))
                    .body(pointOfInterest);
        } catch (PointOfInterestNameAlreadyExistsException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{pointOfInterestId}")
    @Operation(
            summary = "Update a point of interest endpoint",
            description = "Update a point of interest according to the id."
    )
    public ResponseEntity<PointOfInterest> updatePointOfInterest(@PathVariable UUID pointOfInterestId,
                                                   @RequestBody CreatePointOfInterestRequest pointOfInterestToUpdate) {
        try {
            PointOfInterest pointOfInterest = pointOfInterestService.updatePointOfInterest(
                    pointOfInterestId, pointOfInterestToUpdate.getName(), pointOfInterestToUpdate.getDescription(),
                    pointOfInterestToUpdate.getCity()
            );
            return ResponseEntity.ok(pointOfInterest);
        } catch (PointOfInterestNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        } catch (PointOfInterestNameAlreadyExistsException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{pointOfInterestId}")
    @Operation(
            summary = "Delete a point of interest endpoint",
            description = "Delete an existing point of interest according to the id."
    )
    public ResponseEntity<Void> deletePointOfInterest(@PathVariable UUID pointOfInterestId) {
        try {
            pointOfInterestService.deletePointOfInterest(pointOfInterestId);
            return ResponseEntity.ok().build();
        } catch (PointOfInterestNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
