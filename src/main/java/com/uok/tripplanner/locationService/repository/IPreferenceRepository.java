package com.uok.tripplanner.locationService.repository;

import com.uok.tripplanner.locationService.entity.Preference;
import com.uok.tripplanner.locationService.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IPreferenceRepository extends JpaRepository<Preference, Integer> {

    Set<Preference> findAllByLocationId(Integer locationId);
}
