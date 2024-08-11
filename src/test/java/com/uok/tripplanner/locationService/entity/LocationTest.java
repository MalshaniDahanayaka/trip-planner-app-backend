package com.uok.tripplanner.locationService.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.uok.tripplanner.authservice.user.User;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class LocationTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Location#Location()}
     *   <li>{@link Location#setAverageAllocatedTime(String)}
     *   <li>{@link Location#setDescription(String)}
     *   <li>{@link Location#setEvents(Set)}
     *   <li>{@link Location#setId(Integer)}
     *   <li>{@link Location#setLatitude(Double)}
     *   <li>{@link Location#setLocationImages(Set)}
     *   <li>{@link Location#setLocationName(String)}
     *   <li>{@link Location#setLongitude(Double)}
     *   <li>{@link Location#setPreferences(Set)}
     *   <li>{@link Location#setRating(Double)}
     *   <li>{@link Location#setReviews(Set)}
     *   <li>{@link Location#setUser(User)}
     *   <li>{@link Location#getAverageAllocatedTime()}
     *   <li>{@link Location#getDescription()}
     *   <li>{@link Location#getEvents()}
     *   <li>{@link Location#getId()}
     *   <li>{@link Location#getLatitude()}
     *   <li>{@link Location#getLocationImages()}
     *   <li>{@link Location#getLocationName()}
     *   <li>{@link Location#getLongitude()}
     *   <li>{@link Location#getPreferences()}
     *   <li>{@link Location#getRating()}
     *   <li>{@link Location#getReviews()}
     *   <li>{@link Location#getUser()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Location actualLocation = new Location();
        actualLocation.setAverageAllocatedTime("Average Allocated Time");
        actualLocation.setDescription("The characteristics of someone or something");
        HashSet<Event> events = new HashSet<>();
        actualLocation.setEvents(events);
        actualLocation.setId(1);
        actualLocation.setLatitude(10.0d);
        HashSet<LocationImage> locationImages = new HashSet<>();
        actualLocation.setLocationImages(locationImages);
        actualLocation.setLocationName("Location Name");
        actualLocation.setLongitude(10.0d);
        HashSet<Preference> preferences = new HashSet<>();
        actualLocation.setPreferences(preferences);
        actualLocation.setRating(10.0d);
        HashSet<Review> reviews = new HashSet<>();
        actualLocation.setReviews(reviews);
        User user = new User();
        actualLocation.setUser(user);
        assertEquals("Average Allocated Time", actualLocation.getAverageAllocatedTime());
        assertEquals("The characteristics of someone or something", actualLocation.getDescription());
        assertSame(events, actualLocation.getEvents());
        assertEquals(1, actualLocation.getId().intValue());
        assertEquals(10.0d, actualLocation.getLatitude().doubleValue());
        assertSame(locationImages, actualLocation.getLocationImages());
        assertEquals("Location Name", actualLocation.getLocationName());
        assertEquals(10.0d, actualLocation.getLongitude().doubleValue());
        assertSame(preferences, actualLocation.getPreferences());
        assertEquals(10.0d, actualLocation.getRating().doubleValue());
        assertSame(reviews, actualLocation.getReviews());
        assertSame(user, actualLocation.getUser());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Location#Location(Integer, String, String, Double, Double, Double, String, Set, Set, Set, Set, User)}
     *   <li>{@link Location#setAverageAllocatedTime(String)}
     *   <li>{@link Location#setDescription(String)}
     *   <li>{@link Location#setEvents(Set)}
     *   <li>{@link Location#setId(Integer)}
     *   <li>{@link Location#setLatitude(Double)}
     *   <li>{@link Location#setLocationImages(Set)}
     *   <li>{@link Location#setLocationName(String)}
     *   <li>{@link Location#setLongitude(Double)}
     *   <li>{@link Location#setPreferences(Set)}
     *   <li>{@link Location#setRating(Double)}
     *   <li>{@link Location#setReviews(Set)}
     *   <li>{@link Location#setUser(User)}
     *   <li>{@link Location#getAverageAllocatedTime()}
     *   <li>{@link Location#getDescription()}
     *   <li>{@link Location#getEvents()}
     *   <li>{@link Location#getId()}
     *   <li>{@link Location#getLatitude()}
     *   <li>{@link Location#getLocationImages()}
     *   <li>{@link Location#getLocationName()}
     *   <li>{@link Location#getLongitude()}
     *   <li>{@link Location#getPreferences()}
     *   <li>{@link Location#getRating()}
     *   <li>{@link Location#getReviews()}
     *   <li>{@link Location#getUser()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        HashSet<Review> reviews = new HashSet<>();
        HashSet<Event> events = new HashSet<>();
        HashSet<Preference> preferences = new HashSet<>();
        HashSet<LocationImage> locationImages = new HashSet<>();
        User user = new User();
        Location actualLocation = new Location(1, "Location Name", "The characteristics of someone or something", 10.0d,
                10.0d, 10.0d, "Average Allocated Time", reviews, events, preferences, locationImages, user);
        actualLocation.setAverageAllocatedTime("Average Allocated Time");
        actualLocation.setDescription("The characteristics of someone or something");
        HashSet<Event> events2 = new HashSet<>();
        actualLocation.setEvents(events2);
        actualLocation.setId(1);
        actualLocation.setLatitude(10.0d);
        HashSet<LocationImage> locationImages2 = new HashSet<>();
        actualLocation.setLocationImages(locationImages2);
        actualLocation.setLocationName("Location Name");
        actualLocation.setLongitude(10.0d);
        HashSet<Preference> preferences2 = new HashSet<>();
        actualLocation.setPreferences(preferences2);
        actualLocation.setRating(10.0d);
        HashSet<Review> reviews2 = new HashSet<>();
        actualLocation.setReviews(reviews2);
        User user2 = new User();
        actualLocation.setUser(user2);
        assertEquals("Average Allocated Time", actualLocation.getAverageAllocatedTime());
        assertEquals("The characteristics of someone or something", actualLocation.getDescription());
        assertSame(events2, actualLocation.getEvents());
        assertEquals(1, actualLocation.getId().intValue());
        assertEquals(10.0d, actualLocation.getLatitude().doubleValue());
        assertSame(locationImages2, actualLocation.getLocationImages());
        assertEquals("Location Name", actualLocation.getLocationName());
        assertEquals(10.0d, actualLocation.getLongitude().doubleValue());
        assertSame(preferences2, actualLocation.getPreferences());
        assertEquals(10.0d, actualLocation.getRating().doubleValue());
        assertSame(reviews2, actualLocation.getReviews());
        User user3 = actualLocation.getUser();
        assertSame(user2, user3);
        assertEquals(user, user3);
    }
}