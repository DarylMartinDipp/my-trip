package com.dauphine.my_trip.controllers;

import com.dauphine.my_trip.models.City;
import com.dauphine.my_trip.services.CityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

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
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    @GetMapping("/{id}")
    public City getCityById(@PathVariable UUID id) {
        return cityService.getCityById(id);
    }

    @PostMapping
    public City createCity(@RequestBody String name, @RequestBody String country) {
        return cityService.createCity(name, country);
    }

    @PutMapping("/{id}")
    public City updateCity(@PathVariable UUID id, @RequestBody String name, @RequestBody String country) {
        return cityService.updateCity(id, name, country);
    }

    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable UUID id) {
        cityService.deleteCity(id);
    }

}
