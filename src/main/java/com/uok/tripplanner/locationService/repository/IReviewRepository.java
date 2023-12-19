package com.uok.tripplanner.locationService.repository;

import com.uok.tripplanner.locationService.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface IReviewRepository extends JpaRepository<Review, Integer> {

    Set<Review> findAllByLocationId(Integer locationId);
}
