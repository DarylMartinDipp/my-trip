package com.dauphine.my_trip.repositories;

import com.dauphine.my_trip.models.PointOfInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PointOfInterestRepository extends JpaRepository<PointOfInterest, UUID> {
    @Query("SELECT poi FROM PointOfInterest poi WHERE UPPER(poi.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    List<PointOfInterest> findByNameContainingIgnoreCase(@Param("name") String name);

    Optional<PointOfInterest> findByName(String poiName);

    @Query("SELECT poi FROM PointOfInterest poi WHERE poi.city.id = :cityId")
    List<PointOfInterest> findByCity(UUID cityId);
}
