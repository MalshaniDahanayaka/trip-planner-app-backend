package com.uok.tripplanner.locationService.service;

import com.uok.tripplanner.authService.user.User;
import com.uok.tripplanner.authService.user.UserRepository;
import com.uok.tripplanner.locationService.dto.ReviewDto;
import com.uok.tripplanner.locationService.entity.Location;
import com.uok.tripplanner.locationService.entity.Review;
import com.uok.tripplanner.locationService.repository.ILocationRepository;
import com.uok.tripplanner.locationService.repository.IReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record ReviewService(
        IReviewRepository reviewRepository,
        ILocationRepository locationRepository,
        UserRepository userRepository
) {

    public String saveReview(ReviewDto reviewDto) {

        Location location = locationRepository.findById(reviewDto.getLocationId()).orElseThrow();

        User user = userRepository.findByEmail(reviewDto.getUserEmail()).orElseThrow();

        Review review = Review.builder()
                .review(reviewDto.getReview())
                .rating(reviewDto.getRating())
                .location(location)

                .user(user)
                .build();

       // location.getReviews().add(review);
        reviewRepository.save(review);
        return "Review saved successfully";
    }
}
