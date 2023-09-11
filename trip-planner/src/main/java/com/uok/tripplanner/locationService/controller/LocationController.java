package com.uok.tripplanner.locationService.controller;

import com.uok.tripplanner.locationService.dto.LocationDto;
import com.uok.tripplanner.locationService.dto.LocationResponseDto;
import com.uok.tripplanner.locationService.entity.Location;
import com.uok.tripplanner.locationService.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/location")
@Slf4j
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') and hasRole('ROLE_ADMIN')")
    @PostMapping("/save")
    public String saveLocation(@RequestBody LocationDto locationDto) {
        log.info("LocationController: saveLocation");
        return locationService.saveLocation(locationDto);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER') and hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public LocationResponseDto getLocation() {
        log.info("LocationController: getLocation");
        return locationService.getLocation(1);
    }
}
