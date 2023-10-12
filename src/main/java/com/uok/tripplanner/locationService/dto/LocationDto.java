package com.uok.tripplanner.locationService.dto;


import com.uok.tripplanner.locationService.entity.Preference;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
public class LocationDto {

    private String locationName;
    private String description;
    private Double latitude;
    private Double longitude;
    private String userEmail;
    private List<String> preferences;

}
