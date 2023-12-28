package com.uok.tripplanner.locationService.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LocationDto {

    private String locationName;
    private String description;
    private Double latitude;
    private Double longitude;
    private String userEmail;
    private String averageAllocatedTime;
    private List<String> preferences;
    private List<String> events;
    private List<String> images;

}
