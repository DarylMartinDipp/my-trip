package com.dauphine.my_trip.controllers;

import com.dauphine.my_trip.models.City;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
@Tag(
        name = "City controller API",
        description = "City-related endpoints"
)
public class CityController {
    @GetMapping
    public List<City> getAllCities() {
        // TODO
        return null;
    }

    @GetMapping("/{id}")
    public City getCityById(@PathVariable String id) {
        // TODO
        return null;
    }

    @PostMapping
    public City createCity(@RequestBody City city) {
        // TODO
        return null;
    }

    @PutMapping("/{id}")
    public City updateCity(@PathVariable String id, @RequestBody City city) {
        // TODO
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteCity(@PathVariable String id) {
        //TODO
    }

}
