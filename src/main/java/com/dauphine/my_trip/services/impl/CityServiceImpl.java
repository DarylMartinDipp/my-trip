package com.dauphine.my_trip.services.impl;

import com.dauphine.my_trip.models.City;
import com.dauphine.my_trip.services.CityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CityServiceImpl implements CityService {
    private final List<City> temporariesCities;

    public CityServiceImpl(List<City> temporariesCities) {
        this.temporariesCities = temporariesCities;
        temporariesCities.add(new City("Paris", "France"));
        temporariesCities.add(new City("Nice", "France"));
        temporariesCities.add(new City("London", "England"));
        temporariesCities.add(new City("Abidjan", "Ivory Coast"));
        temporariesCities.add(new City("Alger", "Algeria"));
    }

    @Override
    public List<City> getAllCities() {
        return temporariesCities;
    }

    @Override
    public City getCityById(UUID id) {
        return temporariesCities.stream()
                .filter(city -> id.equals(city.getId()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public City createCity(String newCityName, String newCityCountry) {
        City newCity = new City(newCityName, newCityCountry);
        temporariesCities.add(newCity);
        return newCity;
    }

    @Override
    public City updateCity(UUID cityId, String newCityName, String newCityCountry) {
        City cityToUpdate = getCityById(cityId);

        if (cityToUpdate != null) {
            cityToUpdate.setName(newCityName);
            cityToUpdate.setCountry(newCityCountry);
        }

        return cityToUpdate;
    }

    @Override
    public void deleteCity(UUID cityToDeleteId) {
        temporariesCities.removeIf(city -> city.getId().equals(cityToDeleteId));
    }
}
