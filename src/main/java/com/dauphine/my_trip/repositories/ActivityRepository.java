package com.dauphine.my_trip.repositories;

import com.dauphine.my_trip.models.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ActivityRepository extends JpaRepository<Activity, UUID> {
    @Query("SELECT act FROM Activity act WHERE UPPER(act.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    List<Activity> findByNameContainingIgnoreCase(@Param("name") String name);

    Optional<Activity> findByName(String activityName);
}
