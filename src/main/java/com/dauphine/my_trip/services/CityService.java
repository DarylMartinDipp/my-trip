package com.dauphine.my_trip.services;

import com.dauphine.my_trip.exceptions.city.CityNameAlreadyExistsException;
import com.dauphine.my_trip.exceptions.city.CityNotFoundByIdException;
import com.dauphine.my_trip.models.City;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CityService {

    List<City> getAllCities();

    City getCityById(UUID cityId) throws CityNotFoundByIdException;

    Optional<City> getCityByName(String cityName);

    List<City> getCityByNameIgnoreCase(String cityName);

    City createCity(String newCityName, String newCityCountry) throws CityNameAlreadyExistsException;

    City updateCity(UUID cityId, String newCityName, String newCityCountry) throws CityNotFoundByIdException, CityNameAlreadyExistsException;

    void deleteCity(UUID cityToDeleteId) throws CityNotFoundByIdException;
}
