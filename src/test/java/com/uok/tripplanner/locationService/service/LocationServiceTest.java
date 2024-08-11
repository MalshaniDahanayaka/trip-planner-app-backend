package com.uok.tripplanner.locationService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.uok.tripplanner.authservice.user.User;
import com.uok.tripplanner.authservice.user.UserRepository;
import com.uok.tripplanner.exception.ErrorCodes;
import com.uok.tripplanner.locationService.dto.Request.LocationDto;
import com.uok.tripplanner.locationService.dto.Response.LocationResponseDto;
import com.uok.tripplanner.locationService.dto.Response.UserResponseDto;
import com.uok.tripplanner.locationService.entity.Event;
import com.uok.tripplanner.locationService.entity.Location;
import com.uok.tripplanner.locationService.entity.LocationImage;
import com.uok.tripplanner.locationService.entity.Preference;
import com.uok.tripplanner.locationService.entity.Review;
import com.uok.tripplanner.locationService.repository.IEventRepository;
import com.uok.tripplanner.locationService.repository.ILocationImagesRepository;
import com.uok.tripplanner.locationService.repository.ILocationRepository;
import com.uok.tripplanner.locationService.repository.IPreferenceRepository;
import com.uok.tripplanner.locationService.repository.IReviewRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;

import java.util.HashSet;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {LocationService.class, PreferencesService.class, LocationImagesService.class,
        EventService.class})
@ExtendWith(SpringExtension.class)
class LocationServiceTest {
    @MockBean
    private IEventRepository iEventRepository;

    @MockBean
    private ILocationImagesRepository iLocationImagesRepository;

    @MockBean
    private ILocationRepository iLocationRepository;

    @MockBean
    private IPreferenceRepository iPreferenceRepository;

    @MockBean
    private IReviewRepository iReviewRepository;

    @Autowired
    private LocationService locationService;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link LocationService#getLocation(Integer)}
     */
    @Test
    void testGetLocation4() {
        HashSet<Review> reviews = new HashSet<>();
        reviews.add(new Review());
        Location location = mock(Location.class);
        when(location.getUser()).thenReturn(new User());
        when(location.getLatitude()).thenReturn(10.0d);
        when(location.getLongitude()).thenReturn(10.0d);
        when(location.getId()).thenReturn(1);
        when(location.getDescription()).thenReturn("The characteristics of someone or something");
        when(location.getLocationName()).thenReturn("Location Name");
        when(location.getEvents()).thenReturn(new HashSet<>());
        when(location.getLocationImages()).thenReturn(new HashSet<>());
        when(location.getPreferences()).thenReturn(new HashSet<>());
        when(location.getReviews()).thenReturn(new HashSet<>());
        doNothing().when(location).setReviews(Mockito.<Set<Review>>any());
        location.setReviews(reviews);
        Optional<Location> ofResult = Optional.of(location);
        when(iLocationRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        LocationResponseDto actualLocation = locationService.getLocation(1);
        assertNull(actualLocation.getAverageAllocatedTime());
        assertTrue(actualLocation.getReviews().isEmpty());
        assertEquals("The characteristics of someone or something", actualLocation.getDescription());
        assertEquals(1, actualLocation.getId().intValue());
        assertEquals(10.0d, actualLocation.getLongitude().doubleValue());
        assertTrue(actualLocation.getPreferences().isEmpty());
        assertEquals(10.0d, actualLocation.getLatitude().doubleValue());
        assertTrue(actualLocation.getEvents().isEmpty());
        assertEquals("Location Name", actualLocation.getLocationName());
        assertTrue(actualLocation.getLocationImages().isEmpty());
        UserResponseDto userResponseDto = actualLocation.getUserResponseDto();
        assertNull(userResponseDto.getEmail());
        assertNull(userResponseDto.getFirstname());
        assertNull(userResponseDto.getRole());
        assertNull(userResponseDto.getLastname());
        verify(iLocationRepository).findById(Mockito.<Integer>any());
        verify(location, atLeast(1)).getUser();
        verify(location).getLatitude();
        verify(location).getLongitude();
        verify(location).getId();
        verify(location).getDescription();
        verify(location).getLocationName();
        verify(location).getEvents();
        verify(location).getLocationImages();
        verify(location).getPreferences();
        verify(location).getReviews();
        verify(location).setReviews(Mockito.<Set<Review>>any());
    }

    /**
     * Method under test: {@link LocationService#getLocation(Integer)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetLocation5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.uok.tripplanner.authservice.user.User.getFirstname()" because the return value of "com.uok.tripplanner.locationService.entity.Location.getUser()" is null
        //       at com.uok.tripplanner.locationService.service.LocationService.getLocation(LocationService.java:129)
        //   See https://diff.blue/R013 to resolve this issue.

        HashSet<Review> reviews = new HashSet<>();
        reviews.add(new Review());
        Location location = mock(Location.class);
        when(location.getUser()).thenReturn(null);
        when(location.getLatitude()).thenReturn(10.0d);
        when(location.getLongitude()).thenReturn(10.0d);
        when(location.getId()).thenReturn(1);
        when(location.getDescription()).thenReturn("The characteristics of someone or something");
        when(location.getLocationName()).thenReturn("Location Name");
        when(location.getEvents()).thenReturn(new HashSet<>());
        when(location.getLocationImages()).thenReturn(new HashSet<>());
        when(location.getPreferences()).thenReturn(new HashSet<>());
        when(location.getReviews()).thenReturn(new HashSet<>());
        doNothing().when(location).setReviews(Mockito.<Set<Review>>any());
        location.setReviews(reviews);
        Optional<Location> ofResult = Optional.of(location);
        when(iLocationRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        locationService.getLocation(1);
    }

    /**
     * Method under test: {@link LocationService#getLocation(Integer)}
     */
    @Test
    void testGetLocation6() {
        HashSet<Review> reviews = new HashSet<>();
        reviews.add(new Review());

        HashSet<Event> eventSet = new HashSet<>();
        eventSet.add(new Event());
        Location location = mock(Location.class);
        when(location.getUser()).thenReturn(new User());
        when(location.getLatitude()).thenReturn(10.0d);
        when(location.getLongitude()).thenReturn(10.0d);
        when(location.getId()).thenReturn(1);
        when(location.getDescription()).thenReturn("The characteristics of someone or something");
        when(location.getLocationName()).thenReturn("Location Name");
        when(location.getEvents()).thenReturn(eventSet);
        when(location.getLocationImages()).thenReturn(new HashSet<>());
        when(location.getPreferences()).thenReturn(new HashSet<>());
        when(location.getReviews()).thenReturn(new HashSet<>());
        doNothing().when(location).setReviews(Mockito.<Set<Review>>any());
        location.setReviews(reviews);
        Optional<Location> ofResult = Optional.of(location);
        when(iLocationRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        LocationResponseDto actualLocation = locationService.getLocation(1);
        assertNull(actualLocation.getAverageAllocatedTime());
        assertTrue(actualLocation.getReviews().isEmpty());
        assertEquals("The characteristics of someone or something", actualLocation.getDescription());
        assertEquals(1, actualLocation.getId().intValue());
        assertEquals(10.0d, actualLocation.getLongitude().doubleValue());
        assertTrue(actualLocation.getPreferences().isEmpty());
        assertEquals(10.0d, actualLocation.getLatitude().doubleValue());
        assertEquals(1, actualLocation.getEvents().size());
        assertEquals("Location Name", actualLocation.getLocationName());
        assertTrue(actualLocation.getLocationImages().isEmpty());
        UserResponseDto userResponseDto = actualLocation.getUserResponseDto();
        assertNull(userResponseDto.getEmail());
        assertNull(userResponseDto.getFirstname());
        assertNull(userResponseDto.getRole());
        assertNull(userResponseDto.getLastname());
        verify(iLocationRepository).findById(Mockito.<Integer>any());
        verify(location, atLeast(1)).getUser();
        verify(location).getLatitude();
        verify(location).getLongitude();
        verify(location).getId();
        verify(location).getDescription();
        verify(location).getLocationName();
        verify(location).getEvents();
        verify(location).getLocationImages();
        verify(location).getPreferences();
        verify(location).getReviews();
        verify(location).setReviews(Mockito.<Set<Review>>any());
    }

    /**
     * Method under test: {@link LocationService#getLocation(Integer)}
     */
    @Test
    void testGetLocation7() {
        HashSet<Review> reviews = new HashSet<>();
        reviews.add(new Review());

        HashSet<Event> eventSet = new HashSet<>();
        eventSet.add(new Event());
        eventSet.add(new Event());
        Location location = mock(Location.class);
        when(location.getUser()).thenReturn(new User());
        when(location.getLatitude()).thenReturn(10.0d);
        when(location.getLongitude()).thenReturn(10.0d);
        when(location.getId()).thenReturn(1);
        when(location.getDescription()).thenReturn("The characteristics of someone or something");
        when(location.getLocationName()).thenReturn("Location Name");
        when(location.getEvents()).thenReturn(eventSet);
        when(location.getLocationImages()).thenReturn(new HashSet<>());
        when(location.getPreferences()).thenReturn(new HashSet<>());
        when(location.getReviews()).thenReturn(new HashSet<>());
        doNothing().when(location).setReviews(Mockito.<Set<Review>>any());
        location.setReviews(reviews);
        Optional<Location> ofResult = Optional.of(location);
        when(iLocationRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        LocationResponseDto actualLocation = locationService.getLocation(1);
        assertNull(actualLocation.getAverageAllocatedTime());
        assertTrue(actualLocation.getReviews().isEmpty());
        assertEquals("The characteristics of someone or something", actualLocation.getDescription());
        assertEquals(1, actualLocation.getId().intValue());
        assertEquals(10.0d, actualLocation.getLongitude().doubleValue());
        assertTrue(actualLocation.getPreferences().isEmpty());
        assertEquals(10.0d, actualLocation.getLatitude().doubleValue());
        assertEquals(1, actualLocation.getEvents().size());
        assertEquals("Location Name", actualLocation.getLocationName());
        assertTrue(actualLocation.getLocationImages().isEmpty());
        UserResponseDto userResponseDto = actualLocation.getUserResponseDto();
        assertNull(userResponseDto.getEmail());
        assertNull(userResponseDto.getFirstname());
        assertNull(userResponseDto.getRole());
        assertNull(userResponseDto.getLastname());
        verify(iLocationRepository).findById(Mockito.<Integer>any());
        verify(location, atLeast(1)).getUser();
        verify(location).getLatitude();
        verify(location).getLongitude();
        verify(location).getId();
        verify(location).getDescription();
        verify(location).getLocationName();
        verify(location).getEvents();
        verify(location).getLocationImages();
        verify(location).getPreferences();
        verify(location).getReviews();
        verify(location).setReviews(Mockito.<Set<Review>>any());
    }

    /**
     * Method under test: {@link LocationService#getLocation(Integer)}
     */
    @Test
    void testGetLocation9() {
        HashSet<Review> reviews = new HashSet<>();
        reviews.add(new Review());

        HashSet<Preference> preferenceSet = new HashSet<>();
        preferenceSet.add(new Preference());
        Location location = mock(Location.class);
        when(location.getUser()).thenReturn(new User());
        when(location.getLatitude()).thenReturn(10.0d);
        when(location.getLongitude()).thenReturn(10.0d);
        when(location.getId()).thenReturn(1);
        when(location.getDescription()).thenReturn("The characteristics of someone or something");
        when(location.getLocationName()).thenReturn("Location Name");
        when(location.getEvents()).thenReturn(new HashSet<>());
        when(location.getLocationImages()).thenReturn(new HashSet<>());
        when(location.getPreferences()).thenReturn(preferenceSet);
        when(location.getReviews()).thenReturn(new HashSet<>());
        doNothing().when(location).setReviews(Mockito.<Set<Review>>any());
        location.setReviews(reviews);
        Optional<Location> ofResult = Optional.of(location);
        when(iLocationRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        LocationResponseDto actualLocation = locationService.getLocation(1);
        assertNull(actualLocation.getAverageAllocatedTime());
        assertTrue(actualLocation.getReviews().isEmpty());
        assertEquals("The characteristics of someone or something", actualLocation.getDescription());
        assertEquals(1, actualLocation.getId().intValue());
        assertEquals(10.0d, actualLocation.getLongitude().doubleValue());
        assertEquals(1, actualLocation.getPreferences().size());
        assertEquals(10.0d, actualLocation.getLatitude().doubleValue());
        assertTrue(actualLocation.getEvents().isEmpty());
        assertEquals("Location Name", actualLocation.getLocationName());
        assertTrue(actualLocation.getLocationImages().isEmpty());
        UserResponseDto userResponseDto = actualLocation.getUserResponseDto();
        assertNull(userResponseDto.getEmail());
        assertNull(userResponseDto.getFirstname());
        assertNull(userResponseDto.getRole());
        assertNull(userResponseDto.getLastname());
        verify(iLocationRepository).findById(Mockito.<Integer>any());
        verify(location, atLeast(1)).getUser();
        verify(location).getLatitude();
        verify(location).getLongitude();
        verify(location).getId();
        verify(location).getDescription();
        verify(location).getLocationName();
        verify(location).getEvents();
        verify(location).getLocationImages();
        verify(location).getPreferences();
        verify(location).getReviews();
        verify(location).setReviews(Mockito.<Set<Review>>any());
    }

    /**
     * Method under test: {@link LocationService#getLocation(Integer)}
     */
    @Test
    void testGetLocation10() {
        HashSet<Review> reviews = new HashSet<>();
        reviews.add(new Review());

        HashSet<Preference> preferenceSet = new HashSet<>();
        preferenceSet.add(new Preference());
        preferenceSet.add(new Preference());
        Location location = mock(Location.class);
        when(location.getUser()).thenReturn(new User());
        when(location.getLatitude()).thenReturn(10.0d);
        when(location.getLongitude()).thenReturn(10.0d);
        when(location.getId()).thenReturn(1);
        when(location.getDescription()).thenReturn("The characteristics of someone or something");
        when(location.getLocationName()).thenReturn("Location Name");
        when(location.getEvents()).thenReturn(new HashSet<>());
        when(location.getLocationImages()).thenReturn(new HashSet<>());
        when(location.getPreferences()).thenReturn(preferenceSet);
        when(location.getReviews()).thenReturn(new HashSet<>());
        doNothing().when(location).setReviews(Mockito.<Set<Review>>any());
        location.setReviews(reviews);
        Optional<Location> ofResult = Optional.of(location);
        when(iLocationRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        LocationResponseDto actualLocation = locationService.getLocation(1);
        assertNull(actualLocation.getAverageAllocatedTime());
        assertTrue(actualLocation.getReviews().isEmpty());
        assertEquals("The characteristics of someone or something", actualLocation.getDescription());
        assertEquals(1, actualLocation.getId().intValue());
        assertEquals(10.0d, actualLocation.getLongitude().doubleValue());
        assertEquals(1, actualLocation.getPreferences().size());
        assertEquals(10.0d, actualLocation.getLatitude().doubleValue());
        assertTrue(actualLocation.getEvents().isEmpty());
        assertEquals("Location Name", actualLocation.getLocationName());
        assertTrue(actualLocation.getLocationImages().isEmpty());
        UserResponseDto userResponseDto = actualLocation.getUserResponseDto();
        assertNull(userResponseDto.getEmail());
        assertNull(userResponseDto.getFirstname());
        assertNull(userResponseDto.getRole());
        assertNull(userResponseDto.getLastname());
        verify(iLocationRepository).findById(Mockito.<Integer>any());
        verify(location, atLeast(1)).getUser();
        verify(location).getLatitude();
        verify(location).getLongitude();
        verify(location).getId();
        verify(location).getDescription();
        verify(location).getLocationName();
        verify(location).getEvents();
        verify(location).getLocationImages();
        verify(location).getPreferences();
        verify(location).getReviews();
        verify(location).setReviews(Mockito.<Set<Review>>any());
    }

    /**
     * Method under test: {@link LocationService#getLocation(Integer)}
     */
    @Test
    void testGetLocation11() {
        when(iLocationRepository.findById(Mockito.<Integer>any())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> locationService.getLocation(1));
        verify(iLocationRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link LocationService#getLocation(Integer)}
     */
    @Test
    void testGetLocation12() {
        HashSet<Review> reviews = new HashSet<>();
        reviews.add(new Review());

        Event event = new Event();
        event.setId(1);

        HashSet<Event> eventSet = new HashSet<>();
        eventSet.add(event);
        Location location = mock(Location.class);
        when(location.getUser()).thenReturn(new User());
        when(location.getLatitude()).thenReturn(10.0d);
        when(location.getLongitude()).thenReturn(10.0d);
        when(location.getId()).thenReturn(1);
        when(location.getDescription()).thenReturn("The characteristics of someone or something");
        when(location.getLocationName()).thenReturn("Location Name");
        when(location.getEvents()).thenReturn(eventSet);
        when(location.getLocationImages()).thenReturn(new HashSet<>());
        when(location.getPreferences()).thenReturn(new HashSet<>());
        when(location.getReviews()).thenReturn(new HashSet<>());
        doNothing().when(location).setReviews(Mockito.<Set<Review>>any());
        location.setReviews(reviews);
        Optional<Location> ofResult = Optional.of(location);
        when(iLocationRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        LocationResponseDto actualLocation = locationService.getLocation(1);
        assertNull(actualLocation.getAverageAllocatedTime());
        assertTrue(actualLocation.getReviews().isEmpty());
        assertEquals("The characteristics of someone or something", actualLocation.getDescription());
        assertEquals(1, actualLocation.getId().intValue());
        assertEquals(10.0d, actualLocation.getLongitude().doubleValue());
        assertTrue(actualLocation.getPreferences().isEmpty());
        assertEquals(10.0d, actualLocation.getLatitude().doubleValue());
        assertEquals(1, actualLocation.getEvents().size());
        assertEquals("Location Name", actualLocation.getLocationName());
        assertTrue(actualLocation.getLocationImages().isEmpty());
        UserResponseDto userResponseDto = actualLocation.getUserResponseDto();
        assertNull(userResponseDto.getEmail());
        assertNull(userResponseDto.getFirstname());
        assertNull(userResponseDto.getRole());
        assertNull(userResponseDto.getLastname());
        verify(iLocationRepository).findById(Mockito.<Integer>any());
        verify(location, atLeast(1)).getUser();
        verify(location).getLatitude();
        verify(location).getLongitude();
        verify(location).getId();
        verify(location).getDescription();
        verify(location).getLocationName();
        verify(location).getEvents();
        verify(location).getLocationImages();
        verify(location).getPreferences();
        verify(location).getReviews();
        verify(location).setReviews(Mockito.<Set<Review>>any());
    }

    /**
     * Method under test: {@link LocationService#getLocation(Integer)}
     */
    @Test
    void testGetLocation13() {
        HashSet<Review> reviews = new HashSet<>();
        reviews.add(new Review());

        Event event = new Event();
        event.setEventName("LocationService: getLocation");

        HashSet<Event> eventSet = new HashSet<>();
        eventSet.add(event);
        Location location = mock(Location.class);
        when(location.getUser()).thenReturn(new User());
        when(location.getLatitude()).thenReturn(10.0d);
        when(location.getLongitude()).thenReturn(10.0d);
        when(location.getId()).thenReturn(1);
        when(location.getDescription()).thenReturn("The characteristics of someone or something");
        when(location.getLocationName()).thenReturn("Location Name");
        when(location.getEvents()).thenReturn(eventSet);
        when(location.getLocationImages()).thenReturn(new HashSet<>());
        when(location.getPreferences()).thenReturn(new HashSet<>());
        when(location.getReviews()).thenReturn(new HashSet<>());
        doNothing().when(location).setReviews(Mockito.<Set<Review>>any());
        location.setReviews(reviews);
        Optional<Location> ofResult = Optional.of(location);
        when(iLocationRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        LocationResponseDto actualLocation = locationService.getLocation(1);
        assertNull(actualLocation.getAverageAllocatedTime());
        assertTrue(actualLocation.getReviews().isEmpty());
        assertEquals("The characteristics of someone or something", actualLocation.getDescription());
        assertEquals(1, actualLocation.getId().intValue());
        assertEquals(10.0d, actualLocation.getLongitude().doubleValue());
        assertTrue(actualLocation.getPreferences().isEmpty());
        assertEquals(10.0d, actualLocation.getLatitude().doubleValue());
        assertEquals(1, actualLocation.getEvents().size());
        assertEquals("Location Name", actualLocation.getLocationName());
        assertTrue(actualLocation.getLocationImages().isEmpty());
        UserResponseDto userResponseDto = actualLocation.getUserResponseDto();
        assertNull(userResponseDto.getEmail());
        assertNull(userResponseDto.getFirstname());
        assertNull(userResponseDto.getRole());
        assertNull(userResponseDto.getLastname());
        verify(iLocationRepository).findById(Mockito.<Integer>any());
        verify(location, atLeast(1)).getUser();
        verify(location).getLatitude();
        verify(location).getLongitude();
        verify(location).getId();
        verify(location).getDescription();
        verify(location).getLocationName();
        verify(location).getEvents();
        verify(location).getLocationImages();
        verify(location).getPreferences();
        verify(location).getReviews();
        verify(location).setReviews(Mockito.<Set<Review>>any());
    }

    /**
     * Method under test: {@link LocationService#saveLocation(LocationDto)}
     */
    @Test
    void testSaveLocation() {
        when(iLocationRepository.findByLocationName(Mockito.<String>any())).thenReturn(new Location());
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(new User()));
        ArrayList<String> preferences = new ArrayList<>();
        ArrayList<String> events = new ArrayList<>();
        assertEquals("Location Already in Database.",
                locationService.saveLocation(new LocationDto("Location Name", "The characteristics of someone or something",
                        10.0d, 10.0d, "jane.doe@example.org", "Average Allocated Time", preferences, events, new ArrayList<>())));
        verify(iLocationRepository).findByLocationName(Mockito.<String>any());
        verify(userRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link LocationService#saveLocation(LocationDto)}
     */
    @Test
    void testSaveLocation2() {
        when(iLocationRepository.findByLocationName(Mockito.<String>any()))
                .thenThrow(new EntityNotFoundException("An error occurred"));
        when(iLocationRepository.saveAndFlush(Mockito.<Location>any()))
                .thenThrow(new EntityNotFoundException("An error occurred"));
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(new User()));
        ArrayList<String> preferences = new ArrayList<>();
        ArrayList<String> events = new ArrayList<>();
        assertEquals(ErrorCodes.LOCATION_SAVING_ERROR.toString(),
                locationService.saveLocation(new LocationDto("Location Name", "The characteristics of someone or something",
                        10.0d, 10.0d, "jane.doe@example.org", "Average Allocated Time", preferences, events, new ArrayList<>())));
        verify(iLocationRepository).findByLocationName(Mockito.<String>any());
        verify(iLocationRepository).saveAndFlush(Mockito.<Location>any());
        verify(userRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link LocationService#saveLocation(LocationDto)}
     */
    @Test
    void testSaveLocation3() {
        when(iLocationRepository.findByLocationName(Mockito.<String>any())).thenReturn(null);
        when(iLocationRepository.saveAndFlush(Mockito.<Location>any())).thenReturn(new Location());
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(new User()));
        ArrayList<String> preferences = new ArrayList<>();
        ArrayList<String> events = new ArrayList<>();
        assertEquals(ErrorCodes.LOCATION_SAVING_ERROR.toString(),
                locationService.saveLocation(new LocationDto("Location Name", "The characteristics of someone or something",
                        10.0d, 10.0d, "jane.doe@example.org", "Average Allocated Time", preferences, events, new ArrayList<>())));
        verify(iLocationRepository).findByLocationName(Mockito.<String>any());
        verify(iLocationRepository).saveAndFlush(Mockito.<Location>any());
        verify(userRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link LocationService#saveLocation(LocationDto)}
     */
    @Test
    void testSaveLocation5() {
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.empty());
        ArrayList<String> preferences = new ArrayList<>();
        ArrayList<String> events = new ArrayList<>();
        assertThrows(EntityNotFoundException.class,
                () -> locationService
                        .saveLocation(new LocationDto("Location Name", "The characteristics of someone or something", 10.0d,
                                10.0d, "jane.doe@example.org", "Average Allocated Time", preferences, events, new ArrayList<>())));
        verify(userRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link LocationService#saveLocation(LocationDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSaveLocation6() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.uok.tripplanner.locationService.dto.Request.LocationDto.getUserEmail()" because "locationDto" is null
        //       at com.uok.tripplanner.locationService.service.LocationService.saveLocation(LocationService.java:32)
        //   See https://diff.blue/R013 to resolve this issue.

        when(iLocationRepository.findByLocationName(Mockito.<String>any())).thenReturn(new Location());
        when(iLocationRepository.saveAndFlush(Mockito.<Location>any())).thenReturn(new Location());
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(new User()));
        locationService.saveLocation(null);
    }

    /**
     * Method under test: {@link LocationService#saveLocation(LocationDto)}
     */
    @Test
    void testSaveLocation7() {
        when(iLocationRepository.findByLocationName(Mockito.<String>any())).thenReturn(null);
        when(iLocationRepository.saveAndFlush(Mockito.<Location>any())).thenReturn(null);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(new User()));
        ArrayList<String> preferences = new ArrayList<>();
        ArrayList<String> events = new ArrayList<>();
        assertEquals(ErrorCodes.LOCATION_SAVING_ERROR.toString(),
                locationService.saveLocation(new LocationDto("Location Name", "The characteristics of someone or something",
                        10.0d, 10.0d, "jane.doe@example.org", "Average Allocated Time", preferences, events, new ArrayList<>())));
        verify(iLocationRepository).findByLocationName(Mockito.<String>any());
        verify(iLocationRepository).saveAndFlush(Mockito.<Location>any());
        verify(userRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link LocationService#saveLocation(LocationDto)}
     */
    @Test
    void testSaveLocation8() {
        when(iLocationRepository.save(Mockito.<Location>any())).thenReturn(new Location());
        when(iLocationRepository.findByLocationName(Mockito.<String>any())).thenReturn(null);
        HashSet<Review> reviews = new HashSet<>();
        HashSet<Event> events = new HashSet<>();
        HashSet<Preference> preferences = new HashSet<>();
        HashSet<LocationImage> locationImages = new HashSet<>();
        when(iLocationRepository.saveAndFlush(Mockito.<Location>any())).thenReturn(new Location(1,
                "Error occurred while saving location", "The characteristics of someone or something", 10.0d, 10.0d, 10.0d,
                "Error occurred while saving location", reviews, events, preferences, locationImages, new User()));
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(new User()));
        ArrayList<String> preferences2 = new ArrayList<>();
        ArrayList<String> events2 = new ArrayList<>();
        assertEquals("success",
                locationService
                        .saveLocation(new LocationDto("Location Name", "The characteristics of someone or something", 10.0d,
                                10.0d, "jane.doe@example.org", "Average Allocated Time", preferences2, events2, new ArrayList<>())));
        verify(iLocationRepository).findByLocationName(Mockito.<String>any());
        verify(iLocationRepository).saveAndFlush(Mockito.<Location>any());
        verify(iLocationRepository).save(Mockito.<Location>any());
        verify(userRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link LocationService#saveLocation(LocationDto)}
     */
    @Test
    void testSaveLocation9() {
        Location location = mock(Location.class);
        when(location.getId()).thenReturn(1);
        doNothing().when(location).setEvents(Mockito.<Set<Event>>any());
        doNothing().when(location).setLocationImages(Mockito.<Set<LocationImage>>any());
        doNothing().when(location).setPreferences(Mockito.<Set<Preference>>any());
        when(iLocationRepository.save(Mockito.<Location>any())).thenReturn(new Location());
        when(iLocationRepository.findByLocationName(Mockito.<String>any())).thenReturn(null);
        when(iLocationRepository.saveAndFlush(Mockito.<Location>any())).thenReturn(location);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(new User()));
        ArrayList<String> preferences = new ArrayList<>();
        ArrayList<String> events = new ArrayList<>();
        assertEquals("success",
                locationService.saveLocation(new LocationDto("Location Name", "The characteristics of someone or something",
                        10.0d, 10.0d, "jane.doe@example.org", "Average Allocated Time", preferences, events, new ArrayList<>())));
        verify(iLocationRepository).findByLocationName(Mockito.<String>any());
        verify(iLocationRepository).saveAndFlush(Mockito.<Location>any());
        verify(iLocationRepository).save(Mockito.<Location>any());
        verify(location).getId();
        verify(location).setEvents(Mockito.<Set<Event>>any());
        verify(location).setLocationImages(Mockito.<Set<LocationImage>>any());
        verify(location).setPreferences(Mockito.<Set<Preference>>any());
        verify(userRepository).findByEmail(Mockito.<String>any());
    }
}

