package com.dauphine.my_trip.repositories;

import com.dauphine.my_trip.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CityRepository extends JpaRepository<City, UUID> {
    @Query("SELECT c FROM City c WHERE UPPER(c.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    List<City> findByNameContainingIgnoreCase(@Param("name") String name);

    Optional<City> findByName(String cityName);
}
