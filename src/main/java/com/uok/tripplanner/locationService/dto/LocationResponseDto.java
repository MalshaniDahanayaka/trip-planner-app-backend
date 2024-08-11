package com.uok.tripplanner.locationService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
@Builder
public class LocationResponseDto {

    private Integer id;
    private String locationName;
    private String description;
    private Double latitude;
    private Double longitude;
    private Set<ReviewResponseDto> reviews;
    private Set<PreferencesResponseDto> preferences;
    private UserResponseDto userResponseDto;
}
