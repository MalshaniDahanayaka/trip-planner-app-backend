package com.uok.tripplanner.locationService.service;

import com.uok.tripplanner.authservice.user.User;
import com.uok.tripplanner.authservice.user.UserRepository;
import com.uok.tripplanner.exception.ErrorCodes;
import com.uok.tripplanner.locationService.dto.Request.LocationDto;
import com.uok.tripplanner.locationService.dto.Response.*;
import com.uok.tripplanner.locationService.entity.*;
import com.uok.tripplanner.locationService.repository.ILocationImagesRepository;
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
        LocationImagesService locationImagesService,
        IPreferenceRepository preferenceRepository,
        ILocationImagesRepository locationImagesRepository,
        EventService eventService) {

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
                .averageAllocatedTime(locationDto.getAverageAllocatedTime())
                .rating(0.0)
                .user(user)
                .build();

        try {
            Location savedLocation = iLocationRepository.saveAndFlush(location);
            if(savedLocation.getId() == null){
                return ErrorCodes.LOCATION_SAVING_ERROR.toString();
            }
            savedLocation.setPreferences(
                    locationDto.getPreferences().stream().map(preference -> Preference.builder()
                            .preference(preference)
                            .location(savedLocation)
                            .build()).collect(java.util.stream.Collectors.toSet())
            );
            savedLocation.setLocationImages(
                    locationDto.getImages().stream().map(image -> LocationImage.builder()
                            .image(image)
                            .location(savedLocation)
                            .build()).collect(java.util.stream.Collectors.toSet())
            );
            savedLocation.setEvents(
                    locationDto.getEvents().stream().map(event -> Event.builder()
                            .eventName(event)
                            .location(savedLocation)
                            .build()).collect(java.util.stream.Collectors.toSet())
            );
            iLocationRepository.save(savedLocation);

        }catch (Exception e){
            log.error(String.valueOf(ErrorCodes.LOCATION_SAVING_ERROR), e);
            return ErrorCodes.LOCATION_SAVING_ERROR.toString();
        }



        return "success";
    }

    public LocationResponseDto getLocation(Integer id) {

        log.info("LocationService: getLocation");
        Location location = iLocationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Location not found with ID: " + id));

        Set<ReviewResponseDto> reviewResponseDto = location.getReviews().stream().map(review -> ReviewResponseDto.builder()
                .review(review.getReview())
                .rating(review.getRating())
                .userEmail(review.getUser().getEmail())
                .build()).collect(java.util.stream.Collectors.toSet());

        Set<PreferencesResponseDto> preferencesResponseDto = location.getPreferences().stream().map(preference -> PreferencesResponseDto.builder()
                .id(preference.getId())
                .preference(preference.getPreference())
                .build()).collect(java.util.stream.Collectors.toSet());

        Set<LocationImageResponseDto> locationImageResponseDto = location.getLocationImages().stream().map(locationImage -> LocationImageResponseDto.builder()
                .id(locationImage.getId())
                .image(locationImage.getImage())
                .build()).collect(java.util.stream.Collectors.toSet());

        Set<EventResponseDto> eventResponseDto = location.getEvents().stream().map(event -> EventResponseDto.builder()
                .id(event.getId())
                .eventName(event.getEventName())
                .build()).collect(java.util.stream.Collectors.toSet());



        return LocationResponseDto.builder()
                .id(location.getId())
                .locationName(location.getLocationName())
                .description(location.getDescription())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .reviews(reviewResponseDto)
                .events(eventResponseDto)
                .preferences(preferencesResponseDto)
                .locationImages(locationImageResponseDto)
                .userResponseDto(new UserResponseDto(
                        location.getUser().getFirstname(),
                        location.getUser().getLastname(),
                        location.getUser().getEmail(),
                        location.getUser().getRole()
                )).build();

    }
}
