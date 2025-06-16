package com.dauphine.my_trip.controllers;

import com.dauphine.my_trip.dto.CreateCityRequest;
import com.dauphine.my_trip.exceptions.city.CityNameAlreadyExistsException;
import com.dauphine.my_trip.exceptions.city.CityNotFoundByIdException;
import com.dauphine.my_trip.models.City;
import com.dauphine.my_trip.services.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cities")
@Tag(
        name = "City controller API",
        description = "City-related endpoints"
)
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    @Operation(
            summary = "Get all cities endpoint",
            description = "Return all cities that are in the database, sorted " +
                    "by alphabetical order."
    )
    public ResponseEntity<List<City>> getAllCities(@RequestParam (required = false) String cityName) {
        List<City> citiesToGet = cityName == null || cityName.isBlank() ?
                cityService.getAllCities() : cityService.getCityByNameIgnoreCase(cityName);
        citiesToGet.sort(Comparator.comparing(City::getName));
        return ResponseEntity.ok(citiesToGet);
    }

    @GetMapping("/{cityId}")
    @Operation(
            summary = "Get a city by ID endpoint",
            description = "Return a certain city according to its id."
    )
    public ResponseEntity<City> getCityById(@PathVariable UUID cityId) {
        try {
            final City cityToGet = cityService.getCityById(cityId);
            return ResponseEntity.ok(cityToGet);
        } catch (CityNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Operation(
            summary = "Create a new city endpoint",
            description = "Create a new city with all its data."
    )
    public ResponseEntity<City> createCity(@RequestBody CreateCityRequest cityToCreate) {
        try {
            City city = cityService.createCity(
                    cityToCreate.getName(), cityToCreate.getCountry()
            );
            return ResponseEntity
                    .created(URI.create("cities/" + city.getId()))
                    .body(city);
        } catch (CityNameAlreadyExistsException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{cityId}")
    @Operation(
            summary = "Update a city endpoint",
            description = "Update a city according to the id."
    )
    public ResponseEntity<City> updateCity(@PathVariable UUID cityId, @RequestBody CreateCityRequest cityToUpdate) {
        try {
            City city = cityService.updateCity(
                    cityId, cityToUpdate.getName(), cityToUpdate.getCountry()
            );
            return ResponseEntity.ok(city);
        } catch (CityNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        } catch (CityNameAlreadyExistsException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{cityId}")
    @Operation(
            summary = "Delete a city endpoint",
            description = "Delete an existing city according to the id."
    )
    public ResponseEntity<Void> deleteCity(@PathVariable UUID cityId) {
        try {
            cityService.deleteCity(cityId);
            return ResponseEntity.ok().build();
        } catch (CityNotFoundByIdException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
