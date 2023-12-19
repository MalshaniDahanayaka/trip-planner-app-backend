package com.uok.tripplanner.tripPlanService.controller;

import com.uok.tripplanner.tripPlanService.Service.TripPlannerService;
import com.uok.tripplanner.tripPlanService.dto.TripPlannerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/v1/trip-plan")
public class TripPlannerController {

    private final TripPlannerService tripPlannerService;

    TripPlannerController(TripPlannerService tripPlannerService) {
        this.tripPlannerService = tripPlannerService;
    }

    @GetMapping("/{id}")
    public Optional<TripPlannerDto> getTripPlan(@PathVariable("id") Long id) {
        log.info("Get trip plan request received for id: {}", id);
        return tripPlannerService.getTripPlan(id);
    }

    @PostMapping("/save")
    public String saveTripPlan(@RequestBody TripPlannerDto tripPlannerDto) {
        return tripPlannerService.saveTripPlan(tripPlannerDto);
    }

    @GetMapping("/all")
    public Optional<List<TripPlannerDto>> getAllTripPlans() {
        return tripPlannerService.getAllTripPlans();
    }

}
