package com.uok.tripplanner.tripPlanService.entity;

import com.uok.tripplanner.authservice.user.User;
import com.uok.tripplanner.tripPlanService.dao.SelectEventsAndLocations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TripPlansTest {

    private TripPlans tripPlans;
    private TripPlans tripPlans2;

    @BeforeEach
    void setUp() {
        List<SelectEventsAndLocations> selectEventsAndLocationsList = Arrays.asList(new SelectEventsAndLocations());
        User user = new User();

        tripPlans = TripPlans.builder()
                .id(1L)
                .journeyStartingPlace("StartingPlace")
                .startingDate("2022-03-01")
                .startingTime("08:00")
                .journeyEndingPlace("EndingPlace")
                .selectEventsAndLocationsListJson("[{\"eventNumber\":0,\"eventName\":null,\"allocatedTime\":null,\"preferences\":null,\"locationName\":null,\"latitude\":0.0,\"longitude\":0.0}]")
                .user(user)
                .build();

        tripPlans2 = TripPlans.builder()
                .id(1L)
                .journeyStartingPlace("StartingPlace")
                .startingDate("2022-03-01")
                .startingTime("08:00")
                .journeyEndingPlace("EndingPlace")
                .selectEventsAndLocationsListJson("[{\"eventNumber\":0,\"eventName\":null,\"allocatedTime\":null,\"preferences\":null,\"locationName\":null,\"latitude\":0.0,\"longitude\":0.0}]")
                .user(user)
                .build();
    }

    // existing tests...

    @Test
    void testEquals() {
        assertEquals(tripPlans, tripPlans2);
    }

    @Test
    void testHashCode() {
        assertEquals(tripPlans.hashCode(), tripPlans2.hashCode());
    }

    @Test
    void testToString() {
        String expected = "TripPlans(id=1, journeyStartingPlace=StartingPlace, startingDate=2022-03-01, startingTime=08:00, journeyEndingPlace=EndingPlace, selectEventsAndLocationsListJson=[{\"eventNumber\":0,\"eventName\":null,\"allocatedTime\":null,\"preferences\":null,\"locationName\":null,\"latitude\":0.0,\"longitude\":0.0}], user=User(id=null, firstname=null, lastname=null, email=null, password=null, role=null, tokens=null))";
        assertEquals(expected, tripPlans.toString());
    }

    @Test
    void testSetters() {
        tripPlans.setJourneyStartingPlace("NewStartingPlace");
        tripPlans.setStartingDate("2022-03-02");
        tripPlans.setStartingTime("09:00");
        tripPlans.setJourneyEndingPlace("NewEndingPlace");
        tripPlans.setSelectEventsAndLocationsList(Arrays.asList(new SelectEventsAndLocations()));
        tripPlans.setUser(new User());

        assertEquals("NewStartingPlace", tripPlans.getJourneyStartingPlace());
        assertEquals("2022-03-02", tripPlans.getStartingDate());
        assertEquals("09:00", tripPlans.getStartingTime());
        assertEquals("NewEndingPlace", tripPlans.getJourneyEndingPlace());
        assertEquals(Arrays.asList(new SelectEventsAndLocations()), tripPlans.getSelectEventsAndLocationsList());
        assertEquals(new User(), tripPlans.getUser());
    }
}