package com.dauphine.my_trip.services;

import com.dauphine.my_trip.models.City;

import java.util.List;

public interface CityService {

    List<City> getAll();

    City getById(int id);

    City create(City city);

    City update(City city);

    void delete(City city);
}
