package com.uok.tripplanner.locationService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.uok.tripplanner.authservice.user.User;
import com.uok.tripplanner.authservice.user.UserRepository;
import com.uok.tripplanner.locationService.dto.Request.ReviewDto;
import com.uok.tripplanner.locationService.entity.Location;
import com.uok.tripplanner.locationService.entity.Review;
import com.uok.tripplanner.locationService.repository.ILocationRepository;
import com.uok.tripplanner.locationService.repository.IReviewRepository;

import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ReviewService.class})
@ExtendWith(SpringExtension.class)
class ReviewServiceTest {
    @MockBean
    private ILocationRepository iLocationRepository;

    @MockBean
    private IReviewRepository iReviewRepository;

    @Autowired
    private ReviewService reviewService;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link ReviewService#saveReview(ReviewDto)}
     */
    @Test
    void testSaveReview() {
        when(iReviewRepository.save(Mockito.<Review>any())).thenReturn(new Review());
        when(iLocationRepository.findById(Mockito.<Integer>any())).thenReturn(Optional.of(new Location()));
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(new User()));
        assertEquals("Review saved successfully", reviewService.saveReview(new ReviewDto()));
        verify(iReviewRepository).save(Mockito.<Review>any());
        verify(iLocationRepository).findById(Mockito.<Integer>any());
        verify(userRepository).findByEmail(Mockito.<String>any());
    }
}

