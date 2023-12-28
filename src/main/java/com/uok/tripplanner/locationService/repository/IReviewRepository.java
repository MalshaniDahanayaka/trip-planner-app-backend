package com.uok.tripplanner.locationService.repository;

import com.uok.tripplanner.locationService.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IReviewRepository extends JpaRepository<Review, Integer> {

    Set<Review> findAllByLocationId(Integer locationId);
}
