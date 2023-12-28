package com.uok.tripplanner.locationService.dto.Response;

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
    private String averageAllocatedTime;
    private Set<ReviewResponseDto> reviews;
    private Set<EventResponseDto> events;
    private Set<PreferencesResponseDto> preferences;
    private Set<LocationImageResponseDto> locationImages;
    private UserResponseDto userResponseDto;

}
