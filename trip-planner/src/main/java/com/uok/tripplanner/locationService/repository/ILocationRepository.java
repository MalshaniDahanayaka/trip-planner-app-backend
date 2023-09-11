package com.uok.tripplanner.locationService.repository;

import com.uok.tripplanner.locationService.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILocationRepository extends JpaRepository<Location, Integer> {

}
