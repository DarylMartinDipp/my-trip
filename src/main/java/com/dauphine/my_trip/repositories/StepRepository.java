package com.dauphine.my_trip.repositories;

import com.dauphine.my_trip.models.Step;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StepRepository extends JpaRepository<Step, UUID> {
    List<Step> findByTripId(UUID tripId);
}
