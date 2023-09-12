package com.uok.tripplanner.locationService.service;

import com.uok.tripplanner.authService.user.User;
import com.uok.tripplanner.authService.user.UserRepository;
import com.uok.tripplanner.locationService.dto.*;
import com.uok.tripplanner.locationService.entity.Location;
import com.uok.tripplanner.locationService.entity.Preference;
import com.uok.tripplanner.locationService.entity.Review;
import com.uok.tripplanner.locationService.repository.ILocationRepository;
import com.uok.tripplanner.locationService.repository.IPreferenceRepository;
import com.uok.tripplanner.locationService.repository.IReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Set;

@Slf4j
@Service
public record LocationService(
        ILocationRepository iLocationRepository,
        UserRepository userRepository,
        IReviewRepository reviewRepository,
        PreferencesService preferencesService,
        IPreferenceRepository preferenceRepository) {

    public String saveLocation(LocationDto locationDto) {


        User user = userRepository.findByEmail(locationDto.getUserEmail())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + locationDto.getUserEmail()));

        try {
            Location locationCheck = iLocationRepository.findByLocationName(locationDto.getLocationName());
            if(locationCheck != null){
                return "Location Already in Database.";
            }
        }catch (EntityNotFoundException e){
            log.info("unique location");
        }

        var location = Location.builder()
                .locationName(locationDto.getLocationName())
                .description(locationDto.getDescription())
                .latitude(locationDto.getLatitude())
                .longitude(locationDto.getLongitude())
                .user(user)
                .build();

        Location savedLocation = iLocationRepository.save(location);

        preferencesService.savePreferences(locationDto.getPreferences(), savedLocation);

        return "success";
    }

    public LocationResponseDto getLocation(Integer id) {
        log.info("LocationService: getLocation");
        Location location = iLocationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Location not found with ID: " + id));

        Set<Review> reviews = reviewRepository.findAllByLocationId(id);
        Set<Preference> preferences = preferenceRepository.findAllByLocationId(id);

        Set<ReviewResponseDto> reviewResponseDtos = reviews.stream().map(review -> ReviewResponseDto.builder()
                .review(review.getReview())
                .rating(review.getRating())
                .userEmail(review.getUser().getEmail())
                .build()).collect(java.util.stream.Collectors.toSet());

        Set<PreferencesResponseDto> preferencesResponseDtos = preferences.stream().map(preference -> PreferencesResponseDto.builder()
                .id(preference.getId())
                .preference(preference.getPreference())
                .build()).collect(java.util.stream.Collectors.toSet());


        return LocationResponseDto.builder()
                .id(location.getId())
                .locationName(location.getLocationName())
                .description(location.getDescription())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .reviews(reviewResponseDtos)
                .preferences(preferencesResponseDtos)
                .userResponseDto(new UserResponseDto(
                        location.getUser().getFirstname(),
                        location.getUser().getLastname(),
                        location.getUser().getEmail(),
                        location.getUser().getRole()
                )).build();

    }
}
