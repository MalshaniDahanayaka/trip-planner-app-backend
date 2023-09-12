package com.uok.tripplanner.locationService.repository;

import com.uok.tripplanner.locationService.entity.Preference;
import com.uok.tripplanner.locationService.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface IPreferenceRepository extends JpaRepository<Preference, Integer> {

    Set<Preference> findAllByLocationId(Integer locationId);
}
