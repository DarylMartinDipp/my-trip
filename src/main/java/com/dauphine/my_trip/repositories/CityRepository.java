package com.dauphine.my_trip.repositories;

import com.dauphine.my_trip.models.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CityRepository extends JpaRepository<City, UUID> {
    Optional<City> findByName(String cityName);
}
