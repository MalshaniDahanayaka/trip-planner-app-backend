package com.uok.tripplanner.locationService.service;

import com.uok.tripplanner.authService.user.Role;
import com.uok.tripplanner.authService.user.User;
import com.uok.tripplanner.authService.user.UserRepository;
import com.uok.tripplanner.locationService.dto.LocationDto;
import com.uok.tripplanner.locationService.dto.LocationResponseDto;
import com.uok.tripplanner.locationService.dto.UserResponseDto;
import com.uok.tripplanner.locationService.entity.Location;
import com.uok.tripplanner.locationService.entity.Preference;
import com.uok.tripplanner.locationService.entity.Review;
import com.uok.tripplanner.locationService.repository.ILocationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
public record LocationService(ILocationRepository iLocationRepository,
                              UserRepository userRepository) {

    public String saveLocation(LocationDto locationDto) {


        User user = userRepository.findByEmail(locationDto.getUserEmail())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + locationDto.getUserEmail()));


        var location = Location.builder()
                .locationName(locationDto.getLocationName())
                .description(locationDto.getDescription())
                .latitude(locationDto.getLatitude())
                .longitude(locationDto.getLongitude())
                .user(user)
                .build();
        iLocationRepository.save(location);
        return "success";
    }

    public LocationResponseDto getLocation(Integer id) {
        log.info("LocationService: getLocation");
        Location location = iLocationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Location not found with ID: " + id));

        return LocationResponseDto.builder()
                .id(location.getId())
                .locationName(location.getLocationName())
                .description(location.getDescription())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .reviews(location.getReviews())
                .preferences(location.getPreferences())
                .userResponseDto(new UserResponseDto(
                        location.getUser().getFirstname(),
                        location.getUser().getLastname(),
                        location.getUser().getEmail(),
                        location.getUser().getRole()
                )).build();
    }
}
