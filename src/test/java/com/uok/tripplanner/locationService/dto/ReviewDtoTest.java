package com.uok.tripplanner.locationService.dto;

import com.uok.tripplanner.locationService.dto.Request.ReviewDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReviewDtoTest {
    private ReviewDto reviewDto;
    private ReviewDto reviewDto2;

    @BeforeEach
    void setUp() {
        reviewDto = ReviewDto.builder()
                .review("Review")
                .rating(5)
                .locationId(1)
                .userEmail("abc@gmail.com")
                .build();

        reviewDto2 = ReviewDto.builder()
                .review("Review")
                .rating(5)
                .locationId(1)
                .userEmail("abc@gmail.com")
                .build();
    }

    @Test
    void testReview() {
        assertEquals("Review", reviewDto.getReview());
    }

    @Test
    void testRating() {
        assertEquals(5, reviewDto.getRating());
    }

    @Test
    void testLocationId() {
        assertEquals(1, reviewDto.getLocationId());
    }

    @Test
    void testUserEmail() {
        assertEquals("abc@gmail.com", reviewDto.getUserEmail());
    }

    @Test
    void testEquals() {
        assertEquals(reviewDto, reviewDto2);
    }

    @Test
    void testHashCode() {
        assertEquals(reviewDto.hashCode(), reviewDto2.hashCode());
    }

    @Test
    void testToString() {
        String expected = "ReviewDto(review=Review, rating=5, locationId=1, userEmail=abc@gmail.com)";
        assertEquals(expected, reviewDto.toString());
    }

    @Test
    void testSetter() {
        reviewDto.setReview("NewReview");
        assertEquals("NewReview", reviewDto.getReview());
    }


}