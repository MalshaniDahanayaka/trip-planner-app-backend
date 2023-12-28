package com.uok.tripplanner.tripPlanService.dto;

import com.uok.tripplanner.authService.user.User;
import com.uok.tripplanner.tripPlanService.dao.SelectEventsAndLocations;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripPlannerDto {

    private Long tripPlanId;
    private String journeyStartingPlace;
    private String startingDate;
    private String startingTime;
    private String journeyEndingPlace;
    private List<SelectEventsAndLocations> selectEventsAndLocationsList;
    private String userEmail;

}
