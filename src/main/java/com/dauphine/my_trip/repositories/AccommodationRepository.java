package com.dauphine.my_trip.repositories;

import com.dauphine.my_trip.models.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccommodationRepository extends JpaRepository<Accommodation, UUID> {
    @Query("SELECT acc FROM Accommodation acc WHERE UPPER(acc.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    List<Accommodation> findByNameContainingIgnoreCase(@Param("name") String name);

    Optional<Accommodation> findByName(String accommodationName);

    @Query("SELECT acc FROM Accommodation acc WHERE acc.city.id = :cityId")
    List<Accommodation> findByCity(UUID cityId);
}
