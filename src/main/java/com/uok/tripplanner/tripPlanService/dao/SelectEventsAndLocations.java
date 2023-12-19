package com.uok.tripplanner.tripPlanService.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SelectEventsAndLocations {

    private int eventNumber;
    private String eventName;
    private String allocatedTime;
    private List<String> preferences;
    private String locationName;
    private double latitude;
    private double longitude;
}
