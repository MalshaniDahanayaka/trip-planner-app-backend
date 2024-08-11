package com.uok.tripplanner.locationService.dto;

import com.uok.tripplanner.locationService.dto.Response.ReviewResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReviewResponseDtoTest {
    private ReviewResponseDto reviewResponseDto;
    private ReviewResponseDto reviewResponseDto2;

    @BeforeEach
    void setUp() {
        reviewResponseDto = ReviewResponseDto.builder()
                .review("Review")
                .rating(5)
                .userEmail("abc@gmail.com")
                .build();

        reviewResponseDto2 = ReviewResponseDto.builder()
                .review("Review")
                .rating(5)
                .userEmail("abc@gmail.com")
                .build();
    }

    @Test
    void testReview() {
        assertEquals("Review", reviewResponseDto.getReview());
    }

    @Test
    void testRating() {
        assertEquals(5, reviewResponseDto.getRating());
    }

    @Test
    void testUserEmail() {
        assertEquals("abc@gmail.com", reviewResponseDto.getUserEmail());
    }

    @Test
    void testEquals() {
        assertEquals(reviewResponseDto, reviewResponseDto2);
    }

    @Test
    void testHashCode() {
        assertEquals(reviewResponseDto.hashCode(), reviewResponseDto2.hashCode());
    }

    @Test
    void testToString() {
        String expected = "ReviewResponseDto(review=Review, rating=5, userEmail=abc@gmail.com)";
        assertEquals(expected, reviewResponseDto.toString());
    }

    @Test
    void testSetter() {
        reviewResponseDto.setReview("NewReview");
        assertEquals("NewReview", reviewResponseDto.getReview());
    }


}