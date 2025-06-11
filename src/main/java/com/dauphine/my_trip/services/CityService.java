package com.dauphine.my_trip.services;

import com.dauphine.my_trip.models.City;

import java.util.List;
import java.util.UUID;

public interface CityService {

    List<City> getAllCities();

    City getCityById(UUID id);

    City createCity(String newCityName, String newCityCountry);

    City updateCity(UUID cityId, String newCityName, String newCityCountry);

    void deleteCity(UUID cityToDeleteId);
}
