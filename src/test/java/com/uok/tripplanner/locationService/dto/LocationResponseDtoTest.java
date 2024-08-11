package com.uok.tripplanner.locationService.dto;

import com.uok.tripplanner.authservice.user.Role;
import com.uok.tripplanner.locationService.dto.Response.LocationResponseDto;
import com.uok.tripplanner.locationService.dto.Response.PreferencesResponseDto;
import com.uok.tripplanner.locationService.dto.Response.ReviewResponseDto;
import com.uok.tripplanner.locationService.dto.Response.UserResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocationResponseDtoTest {

    private LocationResponseDto locationResponseDto;

    @BeforeEach
    void setUp() {
        Set<ReviewResponseDto> reviews = new HashSet<>();
        Set<PreferencesResponseDto> preferences = new HashSet<>();
        UserResponseDto userResponseDto = new UserResponseDto("firstname", "lastname", "email", Role.USER);

        locationResponseDto = LocationResponseDto.builder()
                .id(1)
                .locationName("LocationName")
                .description("Description")
                .latitude(1.0)
                .longitude(1.0)
                .reviews(reviews)
                .preferences(preferences)
                .userResponseDto(userResponseDto)
                .build();
    }

    @Test
    void testId() {
        assertEquals(1, locationResponseDto.getId());
    }

    @Test
    void testLocationName() {
        assertEquals("LocationName", locationResponseDto.getLocationName());
    }

    @Test
    void testDescription() {
        assertEquals("Description", locationResponseDto.getDescription());
    }

    @Test
    void testLatitude() {
        assertEquals(1.0, locationResponseDto.getLatitude());
    }

    @Test
    void testLongitude() {
        assertEquals(1.0, locationResponseDto.getLongitude());
    }

    @Test
    void testReviews() {
        assertEquals(new HashSet<>(), locationResponseDto.getReviews());
    }

    @Test
    void testPreferences() {
        assertEquals(new HashSet<>(), locationResponseDto.getPreferences());
    }

    @Test
    void testUserResponseDto() {
        assertEquals(new UserResponseDto("firstname", "lastname", "email", Role.USER), locationResponseDto.getUserResponseDto());
    }

    @Test
    void testEqualsAndHashCode() {
        LocationResponseDto locationResponseDto2 = LocationResponseDto.builder()
                .id(1)
                .locationName("LocationName")
                .description("Description")
                .latitude(1.0)
                .longitude(1.0)
                .reviews(new HashSet<>())
                .preferences(new HashSet<>())
                .userResponseDto(new UserResponseDto("firstname", "lastname", "email", Role.USER))
                .build();

        assertEquals(locationResponseDto, locationResponseDto2);
        assertEquals(locationResponseDto.hashCode(), locationResponseDto2.hashCode());
    }

}