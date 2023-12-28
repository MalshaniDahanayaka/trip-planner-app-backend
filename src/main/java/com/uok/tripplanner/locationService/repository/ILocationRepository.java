package com.uok.tripplanner.locationService.repository;

import com.uok.tripplanner.locationService.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ILocationRepository extends JpaRepository<Location, Integer> {
    Location findByLocationName(String name);
}
