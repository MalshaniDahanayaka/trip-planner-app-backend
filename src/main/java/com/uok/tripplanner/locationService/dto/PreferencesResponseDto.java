package com.uok.tripplanner.locationService.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class PreferencesResponseDto {
    private Integer id;
    private String preference;
}
