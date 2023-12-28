package com.uok.tripplanner.locationService.repository;

import com.uok.tripplanner.locationService.entity.LocationImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ILocationImagesRepository extends JpaRepository<LocationImage, Long> {

    Set<LocationImage> findAllByLocationId(Integer locationId);
}
