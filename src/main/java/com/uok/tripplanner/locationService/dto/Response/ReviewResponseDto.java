package com.uok.tripplanner.locationService.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class ReviewResponseDto {

    private String review;
    private Integer rating;
    private String userEmail;
}
