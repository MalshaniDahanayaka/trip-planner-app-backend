package com.uok.tripplanner.locationService.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocationDto {

    private String locationName;
    private String description;
    private Double latitude;
    private Double longitude;
    private String userEmail;

}
