package com.dauphine.my_trip.services.impl;

import com.dauphine.my_trip.exceptions.city.CityNameAlreadyExistsException;
import com.dauphine.my_trip.exceptions.city.CityNotFoundByIdException;
import com.dauphine.my_trip.models.City;
import com.dauphine.my_trip.repositories.CityRepository;
import com.dauphine.my_trip.services.CityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public City getCityById(UUID cityId) throws CityNotFoundByIdException {
        return cityRepository.findById(cityId).orElseThrow(() -> new CityNotFoundByIdException(cityId));
    }

    @Override
    public Optional<City> getCityByName(String cityName){
        return cityRepository.findByName(cityName);
    }

    @Override
    public List<City> getCityByNameIgnoreCase(String cityName) {
        return cityRepository.findByNameContainingIgnoreCase(cityName);
    }

    @Override
    public City createCity(String newCityName, String newCityCountry) throws CityNameAlreadyExistsException {
        Optional<City> existingCityByName = getCityByName(newCityName);
        if (existingCityByName.isPresent()) throw new CityNameAlreadyExistsException(newCityName);

        City newCity = new City(newCityName, newCityCountry);
        return cityRepository.save(newCity);
    }

    @Override
    public City updateCity(UUID cityId, String newCityName, String newCityCountry) throws CityNotFoundByIdException, CityNameAlreadyExistsException {
        City cityToUpdate = getCityById(cityId);

        Optional<City> existingCityByName = getCityByName(newCityName);
        if (existingCityByName.isPresent() && !existingCityByName.get().equals(cityToUpdate))
            throw new CityNameAlreadyExistsException(newCityName);

        cityToUpdate.setName(newCityName);
        cityToUpdate.setCountry(newCityCountry);

        return cityRepository.save(cityToUpdate);
    }

    @Override
    public void deleteCity(UUID cityToDeleteId) throws CityNotFoundByIdException {
        getCityById(cityToDeleteId);
        cityRepository.deleteById(cityToDeleteId);
    }
}
