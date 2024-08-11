package com.uok.tripplanner.locationService.entity;

import com.uok.tripplanner.authservice.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReviewTest {

    private Review review;
    private Review review2;

    @BeforeEach
    void setUp() {
        Location location = new Location();
        User user = new User();

        review = Review.builder()
                .id(1)
                .review("Review")
                .rating(5)
                .location(location)
                .user(user)
                .build();

        review2 = Review.builder()
                .id(1)
                .review("Review")
                .rating(5)
                .location(location)
                .user(user)
                .build();
    }

    @Test
    void testId() {
        assertEquals(1, review.getId());
    }

    @Test
    void testReview() {
        assertEquals("Review", review.getReview());
    }

    @Test
    void testRating() {
        assertEquals(5, review.getRating());
    }

    @Test
    void testUser() {
        assertEquals(new User(), review.getUser());
    }

    @Test
    void testSetter() {
        review.setReview("NewReview");
        assertEquals("NewReview", review.getReview());
    }
}