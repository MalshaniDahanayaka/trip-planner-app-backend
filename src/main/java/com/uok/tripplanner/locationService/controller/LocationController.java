package com.uok.tripplanner.locationService.controller;

import com.uok.tripplanner.locationService.dto.Request.LocationDto;
import com.uok.tripplanner.locationService.dto.Response.EventResponseDto;
import com.uok.tripplanner.locationService.dto.Response.LocationResponseDto;
import com.uok.tripplanner.locationService.dto.Request.ReviewDto;
import com.uok.tripplanner.locationService.dto.Response.PreferencesResponseDto;
import com.uok.tripplanner.locationService.entity.Event;
import com.uok.tripplanner.locationService.service.EventService;
import com.uok.tripplanner.locationService.service.LocationService;
import com.uok.tripplanner.locationService.service.PreferencesService;
import com.uok.tripplanner.locationService.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/location")
@Slf4j
public class LocationController {

    private final LocationService locationService;
    private final ReviewService reviewService;
    private final EventService eventService;
    private final PreferencesService preferencesService;

    public LocationController(LocationService locationService, ReviewService reviewService, EventService eventService, PreferencesService preferencesService) {
        this.locationService = locationService;
        this.reviewService = reviewService;
        this.eventService = eventService;
        this.preferencesService = preferencesService;
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN') or hasRole('PREMIUM_USER')")
    @PostMapping("/save")
    public String saveLocation(@RequestBody LocationDto locationDto) {
        log.info("LocationController: saveLocation");
        return locationService.saveLocation(locationDto);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN') or hasRole('PREMIUM_USER')")
    @GetMapping("/{id}")
    public LocationResponseDto getLocation(@PathVariable Integer id) {
        log.info("LocationController: getLocation");
        return locationService.getLocation(id);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') and hasRole('ROLE_ADMIN') or hasRole('PREMIUM_USER')")
    @PostMapping("/review")
    public String saveReview(@RequestBody ReviewDto reviewDto) {
        log.info("LocationController: saveReview");
        return reviewService.saveReview(reviewDto);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') and hasRole('ROLE_ADMIN') or hasRole('PREMIUM_USER')")
    @GetMapping("/events")
    public List<EventResponseDto> getEvents() {
        log.info("LocationController: getEvents");
        return eventService.getEvents();
    }


    @PreAuthorize("hasRole('ROLE_MANAGER') and hasRole('ROLE_ADMIN') or hasRole('PREMIUM_USER')")
    @GetMapping("/preferences")
    public List<PreferencesResponseDto> getPreferences() {
        log.info("LocationController: getPreferences");
        return preferencesService.getPreferences();
    }



}
