package com.uok.tripplanner.locationService.repository;

import com.uok.tripplanner.locationService.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ILocationRepository extends JpaRepository<Location, Integer> {
    Location findByLocationName(String name);
}
