package com.dauphine.my_trip.controllers;

import com.dauphine.my_trip.models.City;
import com.dauphine.my_trip.services.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

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
    public List<City> getAllCities() {
        List <City> citiesToGet = cityService.getAllCities();
        citiesToGet.sort(Comparator.comparing(City::getName));
        return citiesToGet;
    }

    @GetMapping("/{cityId}")
    @Operation(
            summary = "Get a city by ID endpoint",
            description = "Return a certain city according to its id."
    )
    public City getCityById(@PathVariable UUID cityId) {
        return cityService.getCityById(cityId);
    }

    @PostMapping
    @Operation(
            summary = "Create a new city endpoint",
            description = "Create a new city with all its data."
    )
    public City createCity(@RequestBody String name, @RequestBody String country) {
        return cityService.createCity(name, country);
    }

    @PutMapping("/{cityId}")
    @Operation(
            summary = "Update a city endpoint",
            description = "Update a city according to the id."
    )
    public City updateCity(@PathVariable UUID cityId, @RequestBody String name, @RequestBody String country) {
        return cityService.updateCity(cityId, name, country);
    }

    @DeleteMapping("/{cityId}")
    @Operation(
            summary = "Delete a city endpoint",
            description = "Delete an existing city according to the id."
    )
    public void deleteCity(@PathVariable UUID cityId) {
        cityService.deleteCity(cityId);
    }
}
