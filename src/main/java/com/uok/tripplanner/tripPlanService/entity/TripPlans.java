package com.uok.tripplanner.tripPlanService.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uok.tripplanner.authService.user.User;
import com.uok.tripplanner.tripPlanService.dao.SelectEventsAndLocations;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "trip_plans")
public class TripPlans {

    private static final Logger log = LoggerFactory.getLogger(TripPlans.class);

    @Id
    @GeneratedValue
    private Long id;

    private String journeyStartingPlace;
    private String startingDate;
    private String startingTime;
    private String journeyEndingPlace;

    @Column(columnDefinition = "json")
    private String selectEventsAndLocationsListJson;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public void setSelectEventsAndLocationsList(List<SelectEventsAndLocations> selectEventsAndLocationsList) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.selectEventsAndLocationsListJson = objectMapper.writeValueAsString(selectEventsAndLocationsList);
        } catch (JsonProcessingException e) {
            log.error("Error serializing selectEventsAndLocationsList", e);
        }
    }

    public List<SelectEventsAndLocations> getSelectEventsAndLocationsList() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(selectEventsAndLocationsListJson, new TypeReference<List<SelectEventsAndLocations>>() {});
        } catch (JsonProcessingException e) {
            log.error("Error deserializing selectEventsAndLocationsListJson", e);
            return null;
        }
    }
}
