package com.uok.tripplanner.locationService.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class LocationImageResponseDto {

    private int id;
    private String image;

}
