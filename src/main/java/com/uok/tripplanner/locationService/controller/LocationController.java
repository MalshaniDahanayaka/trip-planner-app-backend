package com.uok.tripplanner.locationService.controller;

import com.uok.tripplanner.locationService.dto.LocationDto;
import com.uok.tripplanner.locationService.dto.LocationResponseDto;
import com.uok.tripplanner.locationService.dto.ReviewDto;
import com.uok.tripplanner.locationService.entity.Location;
import com.uok.tripplanner.locationService.service.LocationService;
import com.uok.tripplanner.locationService.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/location")
@Slf4j
public class LocationController {

    private final LocationService locationService;
    private final ReviewService reviewService;

    public LocationController(LocationService locationService, ReviewService reviewService) {
        this.locationService = locationService;
        this.reviewService = reviewService;
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




}
