package com.uok.tripplanner.locationService.dto.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {

    private String review;
    private Integer rating;
    private Integer locationId;
    private String userEmail;

}
