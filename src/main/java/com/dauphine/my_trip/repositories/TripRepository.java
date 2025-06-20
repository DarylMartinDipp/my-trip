package com.dauphine.my_trip.repositories;

import com.dauphine.my_trip.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TripRepository extends JpaRepository<Trip, UUID> {
    @Query("SELECT t FROM Trip t WHERE UPPER(t.title) LIKE UPPER(CONCAT('%', :title, '%'))")
    List<Trip> findByTitleContainingIgnoreCase(@Param("title") String title);

    Optional<Trip> findByTitle(String tripTitle);
}
