package com.uok.tripplanner.locationService.controller;

import static com.uok.tripplanner.exception.ErrorCodes.LOCATION_SAVING_ERROR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.uok.tripplanner.authservice.user.User;
import com.uok.tripplanner.authservice.user.UserRepository;
import com.uok.tripplanner.locationService.dto.Request.LocationDto;
import com.uok.tripplanner.locationService.dto.Request.ReviewDto;
import com.uok.tripplanner.locationService.dto.Response.LocationResponseDto;
import com.uok.tripplanner.locationService.dto.Response.PreferencesResponseDto;
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
import com.uok.tripplanner.locationService.service.EventService;
import com.uok.tripplanner.locationService.service.LocationImagesService;
import com.uok.tripplanner.locationService.service.LocationService;
import com.uok.tripplanner.locationService.service.PreferencesService;
import com.uok.tripplanner.locationService.service.ReviewService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class LocationControllerTest {
    /**
     * Method under test: {@link LocationController#saveLocation(LocationDto)}
     */
    @Test
    void testSaveLocation() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.uok.tripplanner.locationService.dto.Request.LocationDto]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.uok.tripplanner.locationService.dto.Request.LocationDto]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `com.uok.tripplanner.locationService.dto.Request.LocationDto` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
        //    at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 1, column: 2]
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:67)
        //       at com.fasterxml.jackson.databind.DeserializationContext.reportBadDefinition(DeserializationContext.java:1909)
        //       at com.fasterxml.jackson.databind.DatabindContext.reportBadDefinition(DatabindContext.java:408)
        //       at com.fasterxml.jackson.databind.DeserializationContext.handleMissingInstantiator(DeserializationContext.java:1354)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.deserializeFromObjectUsingNonDefault(BeanDeserializerBase.java:1420)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:352)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:185)
        //       at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:323)
        //       at com.fasterxml.jackson.databind.ObjectReader._bindAndClose(ObjectReader.java:2105)
        //       at com.fasterxml.jackson.databind.ObjectReader.readValue(ObjectReader.java:1481)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   See https://diff.blue/R013 to resolve this issue.

        ILocationRepository iLocationRepository = mock(ILocationRepository.class);
        when(iLocationRepository.findByLocationName(Mockito.<String>any())).thenReturn(new Location());
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(new User()));
        IReviewRepository reviewRepository = mock(IReviewRepository.class);
        PreferencesService preferencesService = new PreferencesService(mock(IPreferenceRepository.class));
        LocationImagesService locationImagesService = new LocationImagesService(mock(ILocationImagesRepository.class));
        IPreferenceRepository preferenceRepository = mock(IPreferenceRepository.class);
        ILocationImagesRepository locationImagesRepository = mock(ILocationImagesRepository.class);
        LocationService locationService = new LocationService(iLocationRepository, userRepository, reviewRepository,
                preferencesService, locationImagesService, preferenceRepository, locationImagesRepository,
                new EventService(mock(IEventRepository.class)));

        ReviewService reviewService = new ReviewService(mock(IReviewRepository.class), mock(ILocationRepository.class),
                mock(UserRepository.class));

        EventService eventService = new EventService(mock(IEventRepository.class));
        LocationController locationController = new LocationController(locationService, reviewService, eventService,
                new PreferencesService(mock(IPreferenceRepository.class)));
        ArrayList<String> preferences = new ArrayList<>();
        ArrayList<String> events = new ArrayList<>();
        assertEquals("Location Already in Database.",
                locationController.saveLocation(new LocationDto("Location Name", "The characteristics of someone or something",
                        10.0d, 10.0d, "jane.doe@example.org", "Average Allocated Time", preferences, events, new ArrayList<>())));
        verify(iLocationRepository).findByLocationName(Mockito.<String>any());
        verify(userRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link LocationController#saveLocation(LocationDto)}
     */
    @Test
    void testSaveLocation2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.uok.tripplanner.locationService.dto.Request.LocationDto]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.uok.tripplanner.locationService.dto.Request.LocationDto]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `com.uok.tripplanner.locationService.dto.Request.LocationDto` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
        //    at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 1, column: 2]
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:67)
        //       at com.fasterxml.jackson.databind.DeserializationContext.reportBadDefinition(DeserializationContext.java:1909)
        //       at com.fasterxml.jackson.databind.DatabindContext.reportBadDefinition(DatabindContext.java:408)
        //       at com.fasterxml.jackson.databind.DeserializationContext.handleMissingInstantiator(DeserializationContext.java:1354)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.deserializeFromObjectUsingNonDefault(BeanDeserializerBase.java:1420)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:352)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:185)
        //       at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:323)
        //       at com.fasterxml.jackson.databind.ObjectReader._bindAndClose(ObjectReader.java:2105)
        //       at com.fasterxml.jackson.databind.ObjectReader.readValue(ObjectReader.java:1481)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   See https://diff.blue/R013 to resolve this issue.

        ILocationRepository iLocationRepository = mock(ILocationRepository.class);
        when(iLocationRepository.findByLocationName(Mockito.<String>any())).thenReturn(null);
        when(iLocationRepository.saveAndFlush(Mockito.<Location>any())).thenReturn(new Location());
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(new User()));
        IReviewRepository reviewRepository = mock(IReviewRepository.class);
        PreferencesService preferencesService = new PreferencesService(mock(IPreferenceRepository.class));
        LocationImagesService locationImagesService = new LocationImagesService(mock(ILocationImagesRepository.class));
        IPreferenceRepository preferenceRepository = mock(IPreferenceRepository.class);
        ILocationImagesRepository locationImagesRepository = mock(ILocationImagesRepository.class);
        LocationService locationService = new LocationService(iLocationRepository, userRepository, reviewRepository,
                preferencesService, locationImagesService, preferenceRepository, locationImagesRepository,
                new EventService(mock(IEventRepository.class)));

        ReviewService reviewService = new ReviewService(mock(IReviewRepository.class), mock(ILocationRepository.class),
                mock(UserRepository.class));

        EventService eventService = new EventService(mock(IEventRepository.class));
        LocationController locationController = new LocationController(locationService, reviewService, eventService,
                new PreferencesService(mock(IPreferenceRepository.class)));
        ArrayList<String> preferences = new ArrayList<>();
        ArrayList<String> events = new ArrayList<>();
        assertEquals(LOCATION_SAVING_ERROR.toString(),
                locationController
                        .saveLocation(new LocationDto("Location Name", "The characteristics of someone or something", 10.0d,
                                10.0d, "jane.doe@example.org", "Average Allocated Time", preferences, events, new ArrayList<>())));
        verify(iLocationRepository).findByLocationName(Mockito.<String>any());
        verify(iLocationRepository).saveAndFlush(Mockito.<Location>any());
        verify(userRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link LocationController#saveLocation(LocationDto)}
     */
    @Test
    void testSaveLocation5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.uok.tripplanner.locationService.dto.Request.LocationDto]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.uok.tripplanner.locationService.dto.Request.LocationDto]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `com.uok.tripplanner.locationService.dto.Request.LocationDto` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
        //    at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 1, column: 2]
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:67)
        //       at com.fasterxml.jackson.databind.DeserializationContext.reportBadDefinition(DeserializationContext.java:1909)
        //       at com.fasterxml.jackson.databind.DatabindContext.reportBadDefinition(DatabindContext.java:408)
        //       at com.fasterxml.jackson.databind.DeserializationContext.handleMissingInstantiator(DeserializationContext.java:1354)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.deserializeFromObjectUsingNonDefault(BeanDeserializerBase.java:1420)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:352)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:185)
        //       at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:323)
        //       at com.fasterxml.jackson.databind.ObjectReader._bindAndClose(ObjectReader.java:2105)
        //       at com.fasterxml.jackson.databind.ObjectReader.readValue(ObjectReader.java:1481)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   See https://diff.blue/R013 to resolve this issue.

        ILocationRepository iLocationRepository = mock(ILocationRepository.class);
        when(iLocationRepository.save(Mockito.<Location>any())).thenReturn(new Location());
        when(iLocationRepository.findByLocationName(Mockito.<String>any())).thenReturn(null);
        HashSet<Review> reviews = new HashSet<>();
        HashSet<Event> events = new HashSet<>();
        HashSet<Preference> preferences = new HashSet<>();
        HashSet<LocationImage> locationImages = new HashSet<>();
        when(iLocationRepository.saveAndFlush(Mockito.<Location>any())).thenReturn(new Location(1,
                "LocationController: saveLocation", "The characteristics of someone or something", 10.0d, 10.0d, 10.0d,
                "LocationController: saveLocation", reviews, events, preferences, locationImages, new User()));
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(new User()));
        IReviewRepository reviewRepository = mock(IReviewRepository.class);
        PreferencesService preferencesService = new PreferencesService(mock(IPreferenceRepository.class));
        LocationImagesService locationImagesService = new LocationImagesService(mock(ILocationImagesRepository.class));
        IPreferenceRepository preferenceRepository = mock(IPreferenceRepository.class);
        ILocationImagesRepository locationImagesRepository = mock(ILocationImagesRepository.class);
        LocationService locationService = new LocationService(iLocationRepository, userRepository, reviewRepository,
                preferencesService, locationImagesService, preferenceRepository, locationImagesRepository,
                new EventService(mock(IEventRepository.class)));

        ReviewService reviewService = new ReviewService(mock(IReviewRepository.class), mock(ILocationRepository.class),
                mock(UserRepository.class));

        EventService eventService = new EventService(mock(IEventRepository.class));
        LocationController locationController = new LocationController(locationService, reviewService, eventService,
                new PreferencesService(mock(IPreferenceRepository.class)));
        ArrayList<String> preferences2 = new ArrayList<>();
        ArrayList<String> events2 = new ArrayList<>();
        assertEquals("success",
                locationController
                        .saveLocation(new LocationDto("Location Name", "The characteristics of someone or something", 10.0d,
                                10.0d, "jane.doe@example.org", "Average Allocated Time", preferences2, events2, new ArrayList<>())));
        verify(iLocationRepository).findByLocationName(Mockito.<String>any());
        verify(iLocationRepository).saveAndFlush(Mockito.<Location>any());
        verify(iLocationRepository).save(Mockito.<Location>any());
        verify(userRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link LocationController#saveLocation(LocationDto)}
     */
    @Test
    void testSaveLocation6() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.uok.tripplanner.locationService.dto.Request.LocationDto]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.uok.tripplanner.locationService.dto.Request.LocationDto]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `com.uok.tripplanner.locationService.dto.Request.LocationDto` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
        //    at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 1, column: 2]
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:67)
        //       at com.fasterxml.jackson.databind.DeserializationContext.reportBadDefinition(DeserializationContext.java:1909)
        //       at com.fasterxml.jackson.databind.DatabindContext.reportBadDefinition(DatabindContext.java:408)
        //       at com.fasterxml.jackson.databind.DeserializationContext.handleMissingInstantiator(DeserializationContext.java:1354)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.deserializeFromObjectUsingNonDefault(BeanDeserializerBase.java:1420)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:352)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:185)
        //       at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:323)
        //       at com.fasterxml.jackson.databind.ObjectReader._bindAndClose(ObjectReader.java:2105)
        //       at com.fasterxml.jackson.databind.ObjectReader.readValue(ObjectReader.java:1481)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   See https://diff.blue/R013 to resolve this issue.

        ILocationRepository iLocationRepository = mock(ILocationRepository.class);
        when(iLocationRepository.findByLocationName(Mockito.<String>any())).thenReturn(null);
        when(iLocationRepository.saveAndFlush(Mockito.<Location>any())).thenReturn(null);
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(new User()));
        IReviewRepository reviewRepository = mock(IReviewRepository.class);
        PreferencesService preferencesService = new PreferencesService(mock(IPreferenceRepository.class));
        LocationImagesService locationImagesService = new LocationImagesService(mock(ILocationImagesRepository.class));
        IPreferenceRepository preferenceRepository = mock(IPreferenceRepository.class);
        ILocationImagesRepository locationImagesRepository = mock(ILocationImagesRepository.class);
        LocationService locationService = new LocationService(iLocationRepository, userRepository, reviewRepository,
                preferencesService, locationImagesService, preferenceRepository, locationImagesRepository,
                new EventService(mock(IEventRepository.class)));

        ReviewService reviewService = new ReviewService(mock(IReviewRepository.class), mock(ILocationRepository.class),
                mock(UserRepository.class));

        EventService eventService = new EventService(mock(IEventRepository.class));
        LocationController locationController = new LocationController(locationService, reviewService, eventService,
                new PreferencesService(mock(IPreferenceRepository.class)));
        ArrayList<String> preferences = new ArrayList<>();
        ArrayList<String> events = new ArrayList<>();
        assertEquals(LOCATION_SAVING_ERROR.toString(),
                locationController
                        .saveLocation(new LocationDto("Location Name", "The characteristics of someone or something", 10.0d,
                                10.0d, "jane.doe@example.org", "Average Allocated Time", preferences, events, new ArrayList<>())));
        verify(iLocationRepository).findByLocationName(Mockito.<String>any());
        verify(iLocationRepository).saveAndFlush(Mockito.<Location>any());
        verify(userRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link LocationController#saveLocation(LocationDto)}
     */
    @Test
    void testSaveLocation7() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.uok.tripplanner.locationService.dto.Request.LocationDto]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.uok.tripplanner.locationService.dto.Request.LocationDto]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `com.uok.tripplanner.locationService.dto.Request.LocationDto` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
        //    at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 1, column: 2]
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:67)
        //       at com.fasterxml.jackson.databind.DeserializationContext.reportBadDefinition(DeserializationContext.java:1909)
        //       at com.fasterxml.jackson.databind.DatabindContext.reportBadDefinition(DatabindContext.java:408)
        //       at com.fasterxml.jackson.databind.DeserializationContext.handleMissingInstantiator(DeserializationContext.java:1354)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.deserializeFromObjectUsingNonDefault(BeanDeserializerBase.java:1420)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:352)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:185)
        //       at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:323)
        //       at com.fasterxml.jackson.databind.ObjectReader._bindAndClose(ObjectReader.java:2105)
        //       at com.fasterxml.jackson.databind.ObjectReader.readValue(ObjectReader.java:1481)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   See https://diff.blue/R013 to resolve this issue.

        Location location = mock(Location.class);
        when(location.getId()).thenReturn(1);
        doNothing().when(location).setEvents(Mockito.<Set<Event>>any());
        doNothing().when(location).setLocationImages(Mockito.<Set<LocationImage>>any());
        doNothing().when(location).setPreferences(Mockito.<Set<Preference>>any());
        ILocationRepository iLocationRepository = mock(ILocationRepository.class);
        when(iLocationRepository.save(Mockito.<Location>any())).thenReturn(new Location());
        when(iLocationRepository.findByLocationName(Mockito.<String>any())).thenReturn(null);
        when(iLocationRepository.saveAndFlush(Mockito.<Location>any())).thenReturn(location);
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(new User()));
        IReviewRepository reviewRepository = mock(IReviewRepository.class);
        PreferencesService preferencesService = new PreferencesService(mock(IPreferenceRepository.class));
        LocationImagesService locationImagesService = new LocationImagesService(mock(ILocationImagesRepository.class));
        IPreferenceRepository preferenceRepository = mock(IPreferenceRepository.class);
        ILocationImagesRepository locationImagesRepository = mock(ILocationImagesRepository.class);
        LocationService locationService = new LocationService(iLocationRepository, userRepository, reviewRepository,
                preferencesService, locationImagesService, preferenceRepository, locationImagesRepository,
                new EventService(mock(IEventRepository.class)));

        ReviewService reviewService = new ReviewService(mock(IReviewRepository.class), mock(ILocationRepository.class),
                mock(UserRepository.class));

        EventService eventService = new EventService(mock(IEventRepository.class));
        LocationController locationController = new LocationController(locationService, reviewService, eventService,
                new PreferencesService(mock(IPreferenceRepository.class)));
        ArrayList<String> preferences = new ArrayList<>();
        ArrayList<String> events = new ArrayList<>();
        assertEquals("success",
                locationController
                        .saveLocation(new LocationDto("Location Name", "The characteristics of someone or something", 10.0d,
                                10.0d, "jane.doe@example.org", "Average Allocated Time", preferences, events, new ArrayList<>())));
        verify(iLocationRepository).findByLocationName(Mockito.<String>any());
        verify(iLocationRepository).saveAndFlush(Mockito.<Location>any());
        verify(iLocationRepository).save(Mockito.<Location>any());
        verify(location).getId();
        verify(location).setEvents(Mockito.<Set<Event>>any());
        verify(location).setLocationImages(Mockito.<Set<LocationImage>>any());
        verify(location).setPreferences(Mockito.<Set<Preference>>any());
        verify(userRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link LocationController#saveLocation(LocationDto)}
     */
    @Test
    void testSaveLocation8() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.uok.tripplanner.locationService.dto.Request.LocationDto]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.uok.tripplanner.locationService.dto.Request.LocationDto]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `com.uok.tripplanner.locationService.dto.Request.LocationDto` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
        //    at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 1, column: 2]
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:67)
        //       at com.fasterxml.jackson.databind.DeserializationContext.reportBadDefinition(DeserializationContext.java:1909)
        //       at com.fasterxml.jackson.databind.DatabindContext.reportBadDefinition(DatabindContext.java:408)
        //       at com.fasterxml.jackson.databind.DeserializationContext.handleMissingInstantiator(DeserializationContext.java:1354)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.deserializeFromObjectUsingNonDefault(BeanDeserializerBase.java:1420)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:352)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:185)
        //       at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:323)
        //       at com.fasterxml.jackson.databind.ObjectReader._bindAndClose(ObjectReader.java:2105)
        //       at com.fasterxml.jackson.databind.ObjectReader.readValue(ObjectReader.java:1481)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   See https://diff.blue/R013 to resolve this issue.

        Location location = mock(Location.class);
        when(location.getId()).thenReturn(1);
        doNothing().when(location).setEvents(Mockito.<Set<Event>>any());
        doNothing().when(location).setLocationImages(Mockito.<Set<LocationImage>>any());
        doNothing().when(location).setPreferences(Mockito.<Set<Preference>>any());
        ILocationRepository iLocationRepository = mock(ILocationRepository.class);
        when(iLocationRepository.save(Mockito.<Location>any())).thenReturn(new Location());
        when(iLocationRepository.findByLocationName(Mockito.<String>any())).thenReturn(null);
        when(iLocationRepository.saveAndFlush(Mockito.<Location>any())).thenReturn(location);
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(new User()));
        IReviewRepository reviewRepository = mock(IReviewRepository.class);
        PreferencesService preferencesService = new PreferencesService(mock(IPreferenceRepository.class));
        LocationImagesService locationImagesService = new LocationImagesService(mock(ILocationImagesRepository.class));
        IPreferenceRepository preferenceRepository = mock(IPreferenceRepository.class);
        ILocationImagesRepository locationImagesRepository = mock(ILocationImagesRepository.class);
        LocationService locationService = new LocationService(iLocationRepository, userRepository, reviewRepository,
                preferencesService, locationImagesService, preferenceRepository, locationImagesRepository,
                new EventService(mock(IEventRepository.class)));

        ReviewService reviewService = new ReviewService(mock(IReviewRepository.class), mock(ILocationRepository.class),
                mock(UserRepository.class));

        EventService eventService = new EventService(mock(IEventRepository.class));
        LocationController locationController = new LocationController(locationService, reviewService, eventService,
                new PreferencesService(mock(IPreferenceRepository.class)));

        ArrayList<String> preferences = new ArrayList<>();
        preferences.add("success");
        ArrayList<String> events = new ArrayList<>();
        assertEquals("success",
                locationController
                        .saveLocation(new LocationDto("Location Name", "The characteristics of someone or something", 10.0d,
                                10.0d, "jane.doe@example.org", "Average Allocated Time", preferences, events, new ArrayList<>())));
        verify(iLocationRepository).findByLocationName(Mockito.<String>any());
        verify(iLocationRepository).saveAndFlush(Mockito.<Location>any());
        verify(iLocationRepository).save(Mockito.<Location>any());
        verify(location).getId();
        verify(location).setEvents(Mockito.<Set<Event>>any());
        verify(location).setLocationImages(Mockito.<Set<LocationImage>>any());
        verify(location).setPreferences(Mockito.<Set<Preference>>any());
        verify(userRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link LocationController#saveLocation(LocationDto)}
     */
    @Test
    void testSaveLocation9() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.uok.tripplanner.locationService.dto.Request.LocationDto]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.uok.tripplanner.locationService.dto.Request.LocationDto]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `com.uok.tripplanner.locationService.dto.Request.LocationDto` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
        //    at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 1, column: 2]
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:67)
        //       at com.fasterxml.jackson.databind.DeserializationContext.reportBadDefinition(DeserializationContext.java:1909)
        //       at com.fasterxml.jackson.databind.DatabindContext.reportBadDefinition(DatabindContext.java:408)
        //       at com.fasterxml.jackson.databind.DeserializationContext.handleMissingInstantiator(DeserializationContext.java:1354)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.deserializeFromObjectUsingNonDefault(BeanDeserializerBase.java:1420)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:352)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:185)
        //       at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:323)
        //       at com.fasterxml.jackson.databind.ObjectReader._bindAndClose(ObjectReader.java:2105)
        //       at com.fasterxml.jackson.databind.ObjectReader.readValue(ObjectReader.java:1481)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   See https://diff.blue/R013 to resolve this issue.

        Location location = mock(Location.class);
        when(location.getId()).thenReturn(1);
        doNothing().when(location).setEvents(Mockito.<Set<Event>>any());
        doNothing().when(location).setLocationImages(Mockito.<Set<LocationImage>>any());
        doNothing().when(location).setPreferences(Mockito.<Set<Preference>>any());
        ILocationRepository iLocationRepository = mock(ILocationRepository.class);
        when(iLocationRepository.save(Mockito.<Location>any())).thenReturn(new Location());
        when(iLocationRepository.findByLocationName(Mockito.<String>any())).thenReturn(null);
        when(iLocationRepository.saveAndFlush(Mockito.<Location>any())).thenReturn(location);
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(new User()));
        IReviewRepository reviewRepository = mock(IReviewRepository.class);
        PreferencesService preferencesService = new PreferencesService(mock(IPreferenceRepository.class));
        LocationImagesService locationImagesService = new LocationImagesService(mock(ILocationImagesRepository.class));
        IPreferenceRepository preferenceRepository = mock(IPreferenceRepository.class);
        ILocationImagesRepository locationImagesRepository = mock(ILocationImagesRepository.class);
        LocationService locationService = new LocationService(iLocationRepository, userRepository, reviewRepository,
                preferencesService, locationImagesService, preferenceRepository, locationImagesRepository,
                new EventService(mock(IEventRepository.class)));

        ReviewService reviewService = new ReviewService(mock(IReviewRepository.class), mock(ILocationRepository.class),
                mock(UserRepository.class));

        EventService eventService = new EventService(mock(IEventRepository.class));
        LocationController locationController = new LocationController(locationService, reviewService, eventService,
                new PreferencesService(mock(IPreferenceRepository.class)));

        ArrayList<String> events = new ArrayList<>();
        events.add("success");
        ArrayList<String> preferences = new ArrayList<>();
        assertEquals("success",
                locationController
                        .saveLocation(new LocationDto("Location Name", "The characteristics of someone or something", 10.0d,
                                10.0d, "jane.doe@example.org", "Average Allocated Time", preferences, events, new ArrayList<>())));
        verify(iLocationRepository).findByLocationName(Mockito.<String>any());
        verify(iLocationRepository).saveAndFlush(Mockito.<Location>any());
        verify(iLocationRepository).save(Mockito.<Location>any());
        verify(location).getId();
        verify(location).setEvents(Mockito.<Set<Event>>any());
        verify(location).setLocationImages(Mockito.<Set<LocationImage>>any());
        verify(location).setPreferences(Mockito.<Set<Preference>>any());
        verify(userRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link LocationController#saveLocation(LocationDto)}
     */
    @Test
    void testSaveLocation10() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.uok.tripplanner.locationService.dto.Request.LocationDto]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.uok.tripplanner.locationService.dto.Request.LocationDto]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `com.uok.tripplanner.locationService.dto.Request.LocationDto` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
        //    at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 1, column: 2]
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:67)
        //       at com.fasterxml.jackson.databind.DeserializationContext.reportBadDefinition(DeserializationContext.java:1909)
        //       at com.fasterxml.jackson.databind.DatabindContext.reportBadDefinition(DatabindContext.java:408)
        //       at com.fasterxml.jackson.databind.DeserializationContext.handleMissingInstantiator(DeserializationContext.java:1354)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.deserializeFromObjectUsingNonDefault(BeanDeserializerBase.java:1420)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:352)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:185)
        //       at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:323)
        //       at com.fasterxml.jackson.databind.ObjectReader._bindAndClose(ObjectReader.java:2105)
        //       at com.fasterxml.jackson.databind.ObjectReader.readValue(ObjectReader.java:1481)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   See https://diff.blue/R013 to resolve this issue.

        Location location = mock(Location.class);
        when(location.getId()).thenReturn(1);
        doNothing().when(location).setEvents(Mockito.<Set<Event>>any());
        doNothing().when(location).setLocationImages(Mockito.<Set<LocationImage>>any());
        doNothing().when(location).setPreferences(Mockito.<Set<Preference>>any());
        ILocationRepository iLocationRepository = mock(ILocationRepository.class);
        when(iLocationRepository.save(Mockito.<Location>any())).thenReturn(new Location());
        when(iLocationRepository.findByLocationName(Mockito.<String>any())).thenReturn(null);
        when(iLocationRepository.saveAndFlush(Mockito.<Location>any())).thenReturn(location);
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(new User()));
        IReviewRepository reviewRepository = mock(IReviewRepository.class);
        PreferencesService preferencesService = new PreferencesService(mock(IPreferenceRepository.class));
        LocationImagesService locationImagesService = new LocationImagesService(mock(ILocationImagesRepository.class));
        IPreferenceRepository preferenceRepository = mock(IPreferenceRepository.class);
        ILocationImagesRepository locationImagesRepository = mock(ILocationImagesRepository.class);
        LocationService locationService = new LocationService(iLocationRepository, userRepository, reviewRepository,
                preferencesService, locationImagesService, preferenceRepository, locationImagesRepository,
                new EventService(mock(IEventRepository.class)));

        ReviewService reviewService = new ReviewService(mock(IReviewRepository.class), mock(ILocationRepository.class),
                mock(UserRepository.class));

        EventService eventService = new EventService(mock(IEventRepository.class));
        LocationController locationController = new LocationController(locationService, reviewService, eventService,
                new PreferencesService(mock(IPreferenceRepository.class)));

        ArrayList<String> images = new ArrayList<>();
        images.add("success");
        ArrayList<String> preferences = new ArrayList<>();
        assertEquals("success",
                locationController
                        .saveLocation(new LocationDto("Location Name", "The characteristics of someone or something", 10.0d,
                                10.0d, "jane.doe@example.org", "Average Allocated Time", preferences, new ArrayList<>(), images)));
        verify(iLocationRepository).findByLocationName(Mockito.<String>any());
        verify(iLocationRepository).saveAndFlush(Mockito.<Location>any());
        verify(iLocationRepository).save(Mockito.<Location>any());
        verify(location).getId();
        verify(location).setEvents(Mockito.<Set<Event>>any());
        verify(location).setLocationImages(Mockito.<Set<LocationImage>>any());
        verify(location).setPreferences(Mockito.<Set<Preference>>any());
        verify(userRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link LocationController#saveLocation(LocationDto)}
     */
    @Test
    void testSaveLocation11() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.uok.tripplanner.locationService.dto.Request.LocationDto]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.uok.tripplanner.locationService.dto.Request.LocationDto]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `com.uok.tripplanner.locationService.dto.Request.LocationDto` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
        //    at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 1, column: 2]
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:67)
        //       at com.fasterxml.jackson.databind.DeserializationContext.reportBadDefinition(DeserializationContext.java:1909)
        //       at com.fasterxml.jackson.databind.DatabindContext.reportBadDefinition(DatabindContext.java:408)
        //       at com.fasterxml.jackson.databind.DeserializationContext.handleMissingInstantiator(DeserializationContext.java:1354)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.deserializeFromObjectUsingNonDefault(BeanDeserializerBase.java:1420)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:352)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:185)
        //       at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:323)
        //       at com.fasterxml.jackson.databind.ObjectReader._bindAndClose(ObjectReader.java:2105)
        //       at com.fasterxml.jackson.databind.ObjectReader.readValue(ObjectReader.java:1481)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   See https://diff.blue/R013 to resolve this issue.

        Location location = mock(Location.class);
        when(location.getId()).thenReturn(1);
        doNothing().when(location).setEvents(Mockito.<Set<Event>>any());
        doNothing().when(location).setLocationImages(Mockito.<Set<LocationImage>>any());
        doNothing().when(location).setPreferences(Mockito.<Set<Preference>>any());
        ILocationRepository iLocationRepository = mock(ILocationRepository.class);
        when(iLocationRepository.save(Mockito.<Location>any())).thenReturn(new Location());
        when(iLocationRepository.findByLocationName(Mockito.<String>any())).thenReturn(null);
        when(iLocationRepository.saveAndFlush(Mockito.<Location>any())).thenReturn(location);
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(new User()));
        IReviewRepository reviewRepository = mock(IReviewRepository.class);
        PreferencesService preferencesService = new PreferencesService(mock(IPreferenceRepository.class));
        LocationImagesService locationImagesService = new LocationImagesService(mock(ILocationImagesRepository.class));
        IPreferenceRepository preferenceRepository = mock(IPreferenceRepository.class);
        ILocationImagesRepository locationImagesRepository = mock(ILocationImagesRepository.class);
        LocationService locationService = new LocationService(iLocationRepository, userRepository, reviewRepository,
                preferencesService, locationImagesService, preferenceRepository, locationImagesRepository,
                new EventService(mock(IEventRepository.class)));

        ReviewService reviewService = new ReviewService(mock(IReviewRepository.class), mock(ILocationRepository.class),
                mock(UserRepository.class));

        EventService eventService = new EventService(mock(IEventRepository.class));
        LocationController locationController = new LocationController(locationService, reviewService, eventService,
                new PreferencesService(mock(IPreferenceRepository.class)));

        ArrayList<String> preferences = new ArrayList<>();
        preferences.add("foo");
        preferences.add("success");
        ArrayList<String> events = new ArrayList<>();
        assertEquals("success",
                locationController
                        .saveLocation(new LocationDto("Location Name", "The characteristics of someone or something", 10.0d,
                                10.0d, "jane.doe@example.org", "Average Allocated Time", preferences, events, new ArrayList<>())));
        verify(iLocationRepository).findByLocationName(Mockito.<String>any());
        verify(iLocationRepository).saveAndFlush(Mockito.<Location>any());
        verify(iLocationRepository).save(Mockito.<Location>any());
        verify(location).getId();
        verify(location).setEvents(Mockito.<Set<Event>>any());
        verify(location).setLocationImages(Mockito.<Set<LocationImage>>any());
        verify(location).setPreferences(Mockito.<Set<Preference>>any());
        verify(userRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link LocationController#saveLocation(LocationDto)}
     */
    @Test
    void testSaveLocation12() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.uok.tripplanner.locationService.dto.Request.LocationDto]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.uok.tripplanner.locationService.dto.Request.LocationDto]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `com.uok.tripplanner.locationService.dto.Request.LocationDto` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
        //    at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 1, column: 2]
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:67)
        //       at com.fasterxml.jackson.databind.DeserializationContext.reportBadDefinition(DeserializationContext.java:1909)
        //       at com.fasterxml.jackson.databind.DatabindContext.reportBadDefinition(DatabindContext.java:408)
        //       at com.fasterxml.jackson.databind.DeserializationContext.handleMissingInstantiator(DeserializationContext.java:1354)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.deserializeFromObjectUsingNonDefault(BeanDeserializerBase.java:1420)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:352)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:185)
        //       at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:323)
        //       at com.fasterxml.jackson.databind.ObjectReader._bindAndClose(ObjectReader.java:2105)
        //       at com.fasterxml.jackson.databind.ObjectReader.readValue(ObjectReader.java:1481)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   See https://diff.blue/R013 to resolve this issue.

        Location location = mock(Location.class);
        when(location.getId()).thenReturn(1);
        doNothing().when(location).setEvents(Mockito.<Set<Event>>any());
        doNothing().when(location).setLocationImages(Mockito.<Set<LocationImage>>any());
        doNothing().when(location).setPreferences(Mockito.<Set<Preference>>any());
        ILocationRepository iLocationRepository = mock(ILocationRepository.class);
        when(iLocationRepository.save(Mockito.<Location>any())).thenReturn(new Location());
        when(iLocationRepository.findByLocationName(Mockito.<String>any())).thenReturn(null);
        when(iLocationRepository.saveAndFlush(Mockito.<Location>any())).thenReturn(location);
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(new User()));
        IReviewRepository reviewRepository = mock(IReviewRepository.class);
        PreferencesService preferencesService = new PreferencesService(mock(IPreferenceRepository.class));
        LocationImagesService locationImagesService = new LocationImagesService(mock(ILocationImagesRepository.class));
        IPreferenceRepository preferenceRepository = mock(IPreferenceRepository.class);
        ILocationImagesRepository locationImagesRepository = mock(ILocationImagesRepository.class);
        LocationService locationService = new LocationService(iLocationRepository, userRepository, reviewRepository,
                preferencesService, locationImagesService, preferenceRepository, locationImagesRepository,
                new EventService(mock(IEventRepository.class)));

        ReviewService reviewService = new ReviewService(mock(IReviewRepository.class), mock(ILocationRepository.class),
                mock(UserRepository.class));

        EventService eventService = new EventService(mock(IEventRepository.class));
        LocationController locationController = new LocationController(locationService, reviewService, eventService,
                new PreferencesService(mock(IPreferenceRepository.class)));

        ArrayList<String> events = new ArrayList<>();
        events.add("foo");
        events.add("success");
        ArrayList<String> preferences = new ArrayList<>();
        assertEquals("success",
                locationController
                        .saveLocation(new LocationDto("Location Name", "The characteristics of someone or something", 10.0d,
                                10.0d, "jane.doe@example.org", "Average Allocated Time", preferences, events, new ArrayList<>())));
        verify(iLocationRepository).findByLocationName(Mockito.<String>any());
        verify(iLocationRepository).saveAndFlush(Mockito.<Location>any());
        verify(iLocationRepository).save(Mockito.<Location>any());
        verify(location).getId();
        verify(location).setEvents(Mockito.<Set<Event>>any());
        verify(location).setLocationImages(Mockito.<Set<LocationImage>>any());
        verify(location).setPreferences(Mockito.<Set<Preference>>any());
        verify(userRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link LocationController#saveLocation(LocationDto)}
     */
    @Test
    void testSaveLocation13() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.uok.tripplanner.locationService.dto.Request.LocationDto]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.uok.tripplanner.locationService.dto.Request.LocationDto]
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `com.uok.tripplanner.locationService.dto.Request.LocationDto` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
        //    at [Source: (org.springframework.util.StreamUtils$NonClosingInputStream); line: 1, column: 2]
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:67)
        //       at com.fasterxml.jackson.databind.DeserializationContext.reportBadDefinition(DeserializationContext.java:1909)
        //       at com.fasterxml.jackson.databind.DatabindContext.reportBadDefinition(DatabindContext.java:408)
        //       at com.fasterxml.jackson.databind.DeserializationContext.handleMissingInstantiator(DeserializationContext.java:1354)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.deserializeFromObjectUsingNonDefault(BeanDeserializerBase.java:1420)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:352)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:185)
        //       at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:323)
        //       at com.fasterxml.jackson.databind.ObjectReader._bindAndClose(ObjectReader.java:2105)
        //       at com.fasterxml.jackson.databind.ObjectReader.readValue(ObjectReader.java:1481)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:563)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   See https://diff.blue/R013 to resolve this issue.

        Location location = mock(Location.class);
        when(location.getId()).thenReturn(1);
        doNothing().when(location).setEvents(Mockito.<Set<Event>>any());
        doNothing().when(location).setLocationImages(Mockito.<Set<LocationImage>>any());
        doNothing().when(location).setPreferences(Mockito.<Set<Preference>>any());
        ILocationRepository iLocationRepository = mock(ILocationRepository.class);
        when(iLocationRepository.save(Mockito.<Location>any())).thenReturn(new Location());
        when(iLocationRepository.findByLocationName(Mockito.<String>any())).thenReturn(null);
        when(iLocationRepository.saveAndFlush(Mockito.<Location>any())).thenReturn(location);
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(new User()));
        IReviewRepository reviewRepository = mock(IReviewRepository.class);
        PreferencesService preferencesService = new PreferencesService(mock(IPreferenceRepository.class));
        LocationImagesService locationImagesService = new LocationImagesService(mock(ILocationImagesRepository.class));
        IPreferenceRepository preferenceRepository = mock(IPreferenceRepository.class);
        ILocationImagesRepository locationImagesRepository = mock(ILocationImagesRepository.class);
        LocationService locationService = new LocationService(iLocationRepository, userRepository, reviewRepository,
                preferencesService, locationImagesService, preferenceRepository, locationImagesRepository,
                new EventService(mock(IEventRepository.class)));

        ReviewService reviewService = new ReviewService(mock(IReviewRepository.class), mock(ILocationRepository.class),
                mock(UserRepository.class));

        EventService eventService = new EventService(mock(IEventRepository.class));
        LocationController locationController = new LocationController(locationService, reviewService, eventService,
                new PreferencesService(mock(IPreferenceRepository.class)));

        ArrayList<String> images = new ArrayList<>();
        images.add("foo");
        images.add("success");
        ArrayList<String> preferences = new ArrayList<>();
        assertEquals("success",
                locationController
                        .saveLocation(new LocationDto("Location Name", "The characteristics of someone or something", 10.0d,
                                10.0d, "jane.doe@example.org", "Average Allocated Time", preferences, new ArrayList<>(), images)));
        verify(iLocationRepository).findByLocationName(Mockito.<String>any());
        verify(iLocationRepository).saveAndFlush(Mockito.<Location>any());
        verify(iLocationRepository).save(Mockito.<Location>any());
        verify(location).getId();
        verify(location).setEvents(Mockito.<Set<Event>>any());
        verify(location).setLocationImages(Mockito.<Set<LocationImage>>any());
        verify(location).setPreferences(Mockito.<Set<Preference>>any());
        verify(userRepository).findByEmail(Mockito.<String>any());
    }
    /**
     * Method under test: {@link LocationController#getLocation(Integer)}
     */
    @Test
    void testGetLocation4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: java.lang.NullPointerException: Cannot invoke "com.uok.tripplanner.authservice.user.User.getEmail()" because the return value of "com.uok.tripplanner.locationService.entity.Review.getUser()" is null
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:537)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   java.lang.NullPointerException: Cannot invoke "com.uok.tripplanner.authservice.user.User.getEmail()" because the return value of "com.uok.tripplanner.locationService.entity.Review.getUser()" is null
        //       at com.uok.tripplanner.locationService.service.LocationService.lambda$getLocation$5(LocationService.java:98)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.HashMap$KeySpliterator.forEachRemaining(HashMap.java:1707)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.uok.tripplanner.locationService.service.LocationService.getLocation(LocationService.java:99)
        //       at com.uok.tripplanner.locationService.controller.LocationController.getLocation(LocationController.java:46)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:537)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   See https://diff.blue/R013 to resolve this issue.

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
        ILocationRepository iLocationRepository = mock(ILocationRepository.class);
        when(iLocationRepository.findById(Mockito.<Integer>any())).thenReturn(Optional.of(location));
        UserRepository userRepository = mock(UserRepository.class);
        IReviewRepository reviewRepository = mock(IReviewRepository.class);
        PreferencesService preferencesService = new PreferencesService(mock(IPreferenceRepository.class));
        LocationImagesService locationImagesService = new LocationImagesService(mock(ILocationImagesRepository.class));
        IPreferenceRepository preferenceRepository = mock(IPreferenceRepository.class);
        ILocationImagesRepository locationImagesRepository = mock(ILocationImagesRepository.class);
        LocationService locationService = new LocationService(iLocationRepository, userRepository, reviewRepository,
                preferencesService, locationImagesService, preferenceRepository, locationImagesRepository,
                new EventService(mock(IEventRepository.class)));

        ReviewService reviewService = new ReviewService(mock(IReviewRepository.class), mock(ILocationRepository.class),
                mock(UserRepository.class));

        EventService eventService = new EventService(mock(IEventRepository.class));
        LocationResponseDto actualLocation = (new LocationController(locationService, reviewService, eventService,
                new PreferencesService(mock(IPreferenceRepository.class)))).getLocation(1);
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
     * Method under test: {@link LocationController#getLocation(Integer)}
     */
    @Test
    void testGetLocation6() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: java.lang.NullPointerException: Cannot invoke "com.uok.tripplanner.authservice.user.User.getEmail()" because the return value of "com.uok.tripplanner.locationService.entity.Review.getUser()" is null
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:537)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   java.lang.NullPointerException: Cannot invoke "com.uok.tripplanner.authservice.user.User.getEmail()" because the return value of "com.uok.tripplanner.locationService.entity.Review.getUser()" is null
        //       at com.uok.tripplanner.locationService.service.LocationService.lambda$getLocation$5(LocationService.java:98)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.HashMap$KeySpliterator.forEachRemaining(HashMap.java:1707)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.uok.tripplanner.locationService.service.LocationService.getLocation(LocationService.java:99)
        //       at com.uok.tripplanner.locationService.controller.LocationController.getLocation(LocationController.java:46)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:537)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   See https://diff.blue/R013 to resolve this issue.

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
        ILocationRepository iLocationRepository = mock(ILocationRepository.class);
        when(iLocationRepository.findById(Mockito.<Integer>any())).thenReturn(Optional.of(location));
        UserRepository userRepository = mock(UserRepository.class);
        IReviewRepository reviewRepository = mock(IReviewRepository.class);
        PreferencesService preferencesService = new PreferencesService(mock(IPreferenceRepository.class));
        LocationImagesService locationImagesService = new LocationImagesService(mock(ILocationImagesRepository.class));
        IPreferenceRepository preferenceRepository = mock(IPreferenceRepository.class);
        ILocationImagesRepository locationImagesRepository = mock(ILocationImagesRepository.class);
        LocationService locationService = new LocationService(iLocationRepository, userRepository, reviewRepository,
                preferencesService, locationImagesService, preferenceRepository, locationImagesRepository,
                new EventService(mock(IEventRepository.class)));

        ReviewService reviewService = new ReviewService(mock(IReviewRepository.class), mock(ILocationRepository.class),
                mock(UserRepository.class));

        EventService eventService = new EventService(mock(IEventRepository.class));
        LocationResponseDto actualLocation = (new LocationController(locationService, reviewService, eventService,
                new PreferencesService(mock(IPreferenceRepository.class)))).getLocation(1);
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
     * Method under test: {@link LocationController#getLocation(Integer)}
     */
    @Test
    void testGetLocation7() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: java.lang.NullPointerException: Cannot invoke "com.uok.tripplanner.authservice.user.User.getEmail()" because the return value of "com.uok.tripplanner.locationService.entity.Review.getUser()" is null
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:537)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   java.lang.NullPointerException: Cannot invoke "com.uok.tripplanner.authservice.user.User.getEmail()" because the return value of "com.uok.tripplanner.locationService.entity.Review.getUser()" is null
        //       at com.uok.tripplanner.locationService.service.LocationService.lambda$getLocation$5(LocationService.java:98)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.HashMap$KeySpliterator.forEachRemaining(HashMap.java:1707)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.uok.tripplanner.locationService.service.LocationService.getLocation(LocationService.java:99)
        //       at com.uok.tripplanner.locationService.controller.LocationController.getLocation(LocationController.java:46)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:537)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   See https://diff.blue/R013 to resolve this issue.

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
        ILocationRepository iLocationRepository = mock(ILocationRepository.class);
        when(iLocationRepository.findById(Mockito.<Integer>any())).thenReturn(Optional.of(location));
        UserRepository userRepository = mock(UserRepository.class);
        IReviewRepository reviewRepository = mock(IReviewRepository.class);
        PreferencesService preferencesService = new PreferencesService(mock(IPreferenceRepository.class));
        LocationImagesService locationImagesService = new LocationImagesService(mock(ILocationImagesRepository.class));
        IPreferenceRepository preferenceRepository = mock(IPreferenceRepository.class);
        ILocationImagesRepository locationImagesRepository = mock(ILocationImagesRepository.class);
        LocationService locationService = new LocationService(iLocationRepository, userRepository, reviewRepository,
                preferencesService, locationImagesService, preferenceRepository, locationImagesRepository,
                new EventService(mock(IEventRepository.class)));

        ReviewService reviewService = new ReviewService(mock(IReviewRepository.class), mock(ILocationRepository.class),
                mock(UserRepository.class));

        EventService eventService = new EventService(mock(IEventRepository.class));
        LocationResponseDto actualLocation = (new LocationController(locationService, reviewService, eventService,
                new PreferencesService(mock(IPreferenceRepository.class)))).getLocation(1);
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
     * Method under test: {@link LocationController#getLocation(Integer)}
     */
    @Test
    void testGetLocation9() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: java.lang.NullPointerException: Cannot invoke "com.uok.tripplanner.authservice.user.User.getEmail()" because the return value of "com.uok.tripplanner.locationService.entity.Review.getUser()" is null
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:537)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   java.lang.NullPointerException: Cannot invoke "com.uok.tripplanner.authservice.user.User.getEmail()" because the return value of "com.uok.tripplanner.locationService.entity.Review.getUser()" is null
        //       at com.uok.tripplanner.locationService.service.LocationService.lambda$getLocation$5(LocationService.java:98)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.HashMap$KeySpliterator.forEachRemaining(HashMap.java:1707)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.uok.tripplanner.locationService.service.LocationService.getLocation(LocationService.java:99)
        //       at com.uok.tripplanner.locationService.controller.LocationController.getLocation(LocationController.java:46)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:537)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   See https://diff.blue/R013 to resolve this issue.

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
        ILocationRepository iLocationRepository = mock(ILocationRepository.class);
        when(iLocationRepository.findById(Mockito.<Integer>any())).thenReturn(Optional.of(location));
        UserRepository userRepository = mock(UserRepository.class);
        IReviewRepository reviewRepository = mock(IReviewRepository.class);
        PreferencesService preferencesService = new PreferencesService(mock(IPreferenceRepository.class));
        LocationImagesService locationImagesService = new LocationImagesService(mock(ILocationImagesRepository.class));
        IPreferenceRepository preferenceRepository = mock(IPreferenceRepository.class);
        ILocationImagesRepository locationImagesRepository = mock(ILocationImagesRepository.class);
        LocationService locationService = new LocationService(iLocationRepository, userRepository, reviewRepository,
                preferencesService, locationImagesService, preferenceRepository, locationImagesRepository,
                new EventService(mock(IEventRepository.class)));

        ReviewService reviewService = new ReviewService(mock(IReviewRepository.class), mock(ILocationRepository.class),
                mock(UserRepository.class));

        EventService eventService = new EventService(mock(IEventRepository.class));
        LocationResponseDto actualLocation = (new LocationController(locationService, reviewService, eventService,
                new PreferencesService(mock(IPreferenceRepository.class)))).getLocation(1);
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
     * Method under test: {@link LocationController#getLocation(Integer)}
     */
    @Test
    void testGetLocation10() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: java.lang.NullPointerException: Cannot invoke "com.uok.tripplanner.authservice.user.User.getEmail()" because the return value of "com.uok.tripplanner.locationService.entity.Review.getUser()" is null
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:537)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   java.lang.NullPointerException: Cannot invoke "com.uok.tripplanner.authservice.user.User.getEmail()" because the return value of "com.uok.tripplanner.locationService.entity.Review.getUser()" is null
        //       at com.uok.tripplanner.locationService.service.LocationService.lambda$getLocation$5(LocationService.java:98)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.HashMap$KeySpliterator.forEachRemaining(HashMap.java:1707)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.uok.tripplanner.locationService.service.LocationService.getLocation(LocationService.java:99)
        //       at com.uok.tripplanner.locationService.controller.LocationController.getLocation(LocationController.java:46)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:537)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   See https://diff.blue/R013 to resolve this issue.

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
        ILocationRepository iLocationRepository = mock(ILocationRepository.class);
        when(iLocationRepository.findById(Mockito.<Integer>any())).thenReturn(Optional.of(location));
        UserRepository userRepository = mock(UserRepository.class);
        IReviewRepository reviewRepository = mock(IReviewRepository.class);
        PreferencesService preferencesService = new PreferencesService(mock(IPreferenceRepository.class));
        LocationImagesService locationImagesService = new LocationImagesService(mock(ILocationImagesRepository.class));
        IPreferenceRepository preferenceRepository = mock(IPreferenceRepository.class);
        ILocationImagesRepository locationImagesRepository = mock(ILocationImagesRepository.class);
        LocationService locationService = new LocationService(iLocationRepository, userRepository, reviewRepository,
                preferencesService, locationImagesService, preferenceRepository, locationImagesRepository,
                new EventService(mock(IEventRepository.class)));

        ReviewService reviewService = new ReviewService(mock(IReviewRepository.class), mock(ILocationRepository.class),
                mock(UserRepository.class));

        EventService eventService = new EventService(mock(IEventRepository.class));
        LocationResponseDto actualLocation = (new LocationController(locationService, reviewService, eventService,
                new PreferencesService(mock(IPreferenceRepository.class)))).getLocation(1);
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
     * Method under test: {@link LocationController#getLocation(Integer)}
     */
    @Test
    void testGetLocation12() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: java.lang.NullPointerException: Cannot invoke "com.uok.tripplanner.authservice.user.User.getEmail()" because the return value of "com.uok.tripplanner.locationService.entity.Review.getUser()" is null
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:537)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   java.lang.NullPointerException: Cannot invoke "com.uok.tripplanner.authservice.user.User.getEmail()" because the return value of "com.uok.tripplanner.locationService.entity.Review.getUser()" is null
        //       at com.uok.tripplanner.locationService.service.LocationService.lambda$getLocation$5(LocationService.java:98)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.HashMap$KeySpliterator.forEachRemaining(HashMap.java:1707)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.uok.tripplanner.locationService.service.LocationService.getLocation(LocationService.java:99)
        //       at com.uok.tripplanner.locationService.controller.LocationController.getLocation(LocationController.java:46)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:537)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   See https://diff.blue/R013 to resolve this issue.

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
        ILocationRepository iLocationRepository = mock(ILocationRepository.class);
        when(iLocationRepository.findById(Mockito.<Integer>any())).thenReturn(Optional.of(location));
        UserRepository userRepository = mock(UserRepository.class);
        IReviewRepository reviewRepository = mock(IReviewRepository.class);
        PreferencesService preferencesService = new PreferencesService(mock(IPreferenceRepository.class));
        LocationImagesService locationImagesService = new LocationImagesService(mock(ILocationImagesRepository.class));
        IPreferenceRepository preferenceRepository = mock(IPreferenceRepository.class);
        ILocationImagesRepository locationImagesRepository = mock(ILocationImagesRepository.class);
        LocationService locationService = new LocationService(iLocationRepository, userRepository, reviewRepository,
                preferencesService, locationImagesService, preferenceRepository, locationImagesRepository,
                new EventService(mock(IEventRepository.class)));

        ReviewService reviewService = new ReviewService(mock(IReviewRepository.class), mock(ILocationRepository.class),
                mock(UserRepository.class));

        EventService eventService = new EventService(mock(IEventRepository.class));
        LocationResponseDto actualLocation = (new LocationController(locationService, reviewService, eventService,
                new PreferencesService(mock(IPreferenceRepository.class)))).getLocation(1);
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
     * Method under test: {@link LocationController#getLocation(Integer)}
     */
    @Test
    void testGetLocation13() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: java.lang.NullPointerException: Cannot invoke "com.uok.tripplanner.authservice.user.User.getEmail()" because the return value of "com.uok.tripplanner.locationService.entity.Review.getUser()" is null
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:537)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   java.lang.NullPointerException: Cannot invoke "com.uok.tripplanner.authservice.user.User.getEmail()" because the return value of "com.uok.tripplanner.locationService.entity.Review.getUser()" is null
        //       at com.uok.tripplanner.locationService.service.LocationService.lambda$getLocation$5(LocationService.java:98)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.HashMap$KeySpliterator.forEachRemaining(HashMap.java:1707)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.uok.tripplanner.locationService.service.LocationService.getLocation(LocationService.java:99)
        //       at com.uok.tripplanner.locationService.controller.LocationController.getLocation(LocationController.java:46)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:537)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   See https://diff.blue/R013 to resolve this issue.

        HashSet<Review> reviews = new HashSet<>();
        reviews.add(new Review());

        Event event = new Event();
        event.setEventName("LocationController: getLocation");

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
        ILocationRepository iLocationRepository = mock(ILocationRepository.class);
        when(iLocationRepository.findById(Mockito.<Integer>any())).thenReturn(Optional.of(location));
        UserRepository userRepository = mock(UserRepository.class);
        IReviewRepository reviewRepository = mock(IReviewRepository.class);
        PreferencesService preferencesService = new PreferencesService(mock(IPreferenceRepository.class));
        LocationImagesService locationImagesService = new LocationImagesService(mock(ILocationImagesRepository.class));
        IPreferenceRepository preferenceRepository = mock(IPreferenceRepository.class);
        ILocationImagesRepository locationImagesRepository = mock(ILocationImagesRepository.class);
        LocationService locationService = new LocationService(iLocationRepository, userRepository, reviewRepository,
                preferencesService, locationImagesService, preferenceRepository, locationImagesRepository,
                new EventService(mock(IEventRepository.class)));

        ReviewService reviewService = new ReviewService(mock(IReviewRepository.class), mock(ILocationRepository.class),
                mock(UserRepository.class));

        EventService eventService = new EventService(mock(IEventRepository.class));
        LocationResponseDto actualLocation = (new LocationController(locationService, reviewService, eventService,
                new PreferencesService(mock(IPreferenceRepository.class)))).getLocation(1);
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
     * Method under test: {@link LocationController#getLocation(Integer)}
     */
    @Test
    void testGetLocation15() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: java.lang.NullPointerException: Cannot invoke "com.uok.tripplanner.authservice.user.User.getEmail()" because the return value of "com.uok.tripplanner.locationService.entity.Review.getUser()" is null
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:537)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   java.lang.NullPointerException: Cannot invoke "com.uok.tripplanner.authservice.user.User.getEmail()" because the return value of "com.uok.tripplanner.locationService.entity.Review.getUser()" is null
        //       at com.uok.tripplanner.locationService.service.LocationService.lambda$getLocation$5(LocationService.java:98)
        //       at java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:197)
        //       at java.util.HashMap$KeySpliterator.forEachRemaining(HashMap.java:1707)
        //       at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:509)
        //       at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:499)
        //       at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:921)
        //       at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
        //       at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:682)
        //       at com.uok.tripplanner.locationService.service.LocationService.getLocation(LocationService.java:99)
        //       at com.uok.tripplanner.locationService.controller.LocationController.getLocation(LocationController.java:46)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:537)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:631)
        //   See https://diff.blue/R013 to resolve this issue.

        HashSet<Review> reviews = new HashSet<>();
        reviews.add(new Review());
        Event event = mock(Event.class);
        when(event.getId()).thenReturn(1);
        when(event.getEventName()).thenReturn("Event Name");

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
        ILocationRepository iLocationRepository = mock(ILocationRepository.class);
        when(iLocationRepository.findById(Mockito.<Integer>any())).thenReturn(Optional.of(location));
        UserRepository userRepository = mock(UserRepository.class);
        IReviewRepository reviewRepository = mock(IReviewRepository.class);
        PreferencesService preferencesService = new PreferencesService(mock(IPreferenceRepository.class));
        LocationImagesService locationImagesService = new LocationImagesService(mock(ILocationImagesRepository.class));
        IPreferenceRepository preferenceRepository = mock(IPreferenceRepository.class);
        ILocationImagesRepository locationImagesRepository = mock(ILocationImagesRepository.class);
        LocationService locationService = new LocationService(iLocationRepository, userRepository, reviewRepository,
                preferencesService, locationImagesService, preferenceRepository, locationImagesRepository,
                new EventService(mock(IEventRepository.class)));

        ReviewService reviewService = new ReviewService(mock(IReviewRepository.class), mock(ILocationRepository.class),
                mock(UserRepository.class));

        EventService eventService = new EventService(mock(IEventRepository.class));
        LocationResponseDto actualLocation = (new LocationController(locationService, reviewService, eventService,
                new PreferencesService(mock(IPreferenceRepository.class)))).getLocation(1);
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
        verify(event).getId();
        verify(event).getEventName();
    }

    /**
     * Method under test: {@link LocationController#saveReview(ReviewDto)}
     */
    @Test
    void testSaveReview() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        IReviewRepository reviewRepository = mock(IReviewRepository.class);
        when(reviewRepository.save(Mockito.<Review>any())).thenReturn(new Review());
        ILocationRepository locationRepository = mock(ILocationRepository.class);
        when(locationRepository.findById(Mockito.<Integer>any())).thenReturn(Optional.of(new Location()));
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(new User()));
        ReviewService reviewService = new ReviewService(reviewRepository, locationRepository, userRepository);

        ILocationRepository iLocationRepository = mock(ILocationRepository.class);
        UserRepository userRepository2 = mock(UserRepository.class);
        IReviewRepository reviewRepository2 = mock(IReviewRepository.class);
        PreferencesService preferencesService = new PreferencesService(mock(IPreferenceRepository.class));
        LocationImagesService locationImagesService = new LocationImagesService(mock(ILocationImagesRepository.class));
        IPreferenceRepository preferenceRepository = mock(IPreferenceRepository.class);
        ILocationImagesRepository locationImagesRepository = mock(ILocationImagesRepository.class);
        LocationService locationService = new LocationService(iLocationRepository, userRepository2, reviewRepository2,
                preferencesService, locationImagesService, preferenceRepository, locationImagesRepository,
                new EventService(mock(IEventRepository.class)));

        EventService eventService = new EventService(mock(IEventRepository.class));
        LocationController locationController = new LocationController(locationService, reviewService, eventService,
                new PreferencesService(mock(IPreferenceRepository.class)));
        assertEquals("Review saved successfully", locationController.saveReview(new ReviewDto()));
        verify(reviewRepository).save(Mockito.<Review>any());
        verify(locationRepository).findById(Mockito.<Integer>any());
        verify(userRepository).findByEmail(Mockito.<String>any());
    }
    /**
     * Method under test: {@link LocationController#getEvents()}
     */
    @Test
    void testGetEvents() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        IEventRepository eventRepository = mock(IEventRepository.class);
        when(eventRepository.findAll()).thenReturn(new ArrayList<>());
        EventService eventService = new EventService(eventRepository);
        ILocationRepository iLocationRepository = mock(ILocationRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        IReviewRepository reviewRepository = mock(IReviewRepository.class);
        PreferencesService preferencesService = new PreferencesService(mock(IPreferenceRepository.class));
        LocationImagesService locationImagesService = new LocationImagesService(mock(ILocationImagesRepository.class));
        IPreferenceRepository preferenceRepository = mock(IPreferenceRepository.class);
        ILocationImagesRepository locationImagesRepository = mock(ILocationImagesRepository.class);
        LocationService locationService = new LocationService(iLocationRepository, userRepository, reviewRepository,
                preferencesService, locationImagesService, preferenceRepository, locationImagesRepository,
                new EventService(mock(IEventRepository.class)));

        ReviewService reviewService = new ReviewService(mock(IReviewRepository.class), mock(ILocationRepository.class),
                mock(UserRepository.class));

        assertTrue((new LocationController(locationService, reviewService, eventService,
                new PreferencesService(mock(IPreferenceRepository.class)))).getEvents().isEmpty());
        verify(eventRepository).findAll();
    }

    /**
     * Method under test: {@link LocationController#getPreferences()}
     */
    @Test
    void testGetPreferences() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        IPreferenceRepository preferenceRepository = mock(IPreferenceRepository.class);
        when(preferenceRepository.findAll()).thenReturn(new ArrayList<>());
        PreferencesService preferencesService = new PreferencesService(preferenceRepository);
        ILocationRepository iLocationRepository = mock(ILocationRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        IReviewRepository reviewRepository = mock(IReviewRepository.class);
        PreferencesService preferencesService2 = new PreferencesService(mock(IPreferenceRepository.class));
        LocationImagesService locationImagesService = new LocationImagesService(mock(ILocationImagesRepository.class));
        IPreferenceRepository preferenceRepository2 = mock(IPreferenceRepository.class);
        ILocationImagesRepository locationImagesRepository = mock(ILocationImagesRepository.class);
        LocationService locationService = new LocationService(iLocationRepository, userRepository, reviewRepository,
                preferencesService2, locationImagesService, preferenceRepository2, locationImagesRepository,
                new EventService(mock(IEventRepository.class)));

        ReviewService reviewService = new ReviewService(mock(IReviewRepository.class), mock(ILocationRepository.class),
                mock(UserRepository.class));

        assertTrue((new LocationController(locationService, reviewService, new EventService(mock(IEventRepository.class)),
                preferencesService)).getPreferences().isEmpty());
        verify(preferenceRepository).findAll();
    }
    /**
     * Method under test: {@link LocationController#getPreference(Integer)}
     */
    @Test
    void testGetPreference() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        IPreferenceRepository preferenceRepository = mock(IPreferenceRepository.class);
        when(preferenceRepository.findAllByLocationId(Mockito.<Integer>any())).thenReturn(new HashSet<>());
        PreferencesService preferencesService = new PreferencesService(preferenceRepository);
        ILocationRepository iLocationRepository = mock(ILocationRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        IReviewRepository reviewRepository = mock(IReviewRepository.class);
        PreferencesService preferencesService2 = new PreferencesService(mock(IPreferenceRepository.class));
        LocationImagesService locationImagesService = new LocationImagesService(mock(ILocationImagesRepository.class));
        IPreferenceRepository preferenceRepository2 = mock(IPreferenceRepository.class);
        ILocationImagesRepository locationImagesRepository = mock(ILocationImagesRepository.class);
        LocationService locationService = new LocationService(iLocationRepository, userRepository, reviewRepository,
                preferencesService2, locationImagesService, preferenceRepository2, locationImagesRepository,
                new EventService(mock(IEventRepository.class)));

        ReviewService reviewService = new ReviewService(mock(IReviewRepository.class), mock(ILocationRepository.class),
                mock(UserRepository.class));

        assertTrue((new LocationController(locationService, reviewService, new EventService(mock(IEventRepository.class)),
                preferencesService)).getPreference(1).isEmpty());
        verify(preferenceRepository).findAllByLocationId(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link LocationController#getPreference(Integer)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetPreference2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.uok.tripplanner.locationService.entity.Location.getId()" because the return value of "com.uok.tripplanner.locationService.entity.Preference.getLocation()" is null
        //       at com.uok.tripplanner.locationService.service.PreferencesService.getPreference(PreferencesService.java:40)
        //       at com.uok.tripplanner.locationService.controller.LocationController.getPreference(LocationController.java:75)
        //   See https://diff.blue/R013 to resolve this issue.

        HashSet<Preference> preferenceSet = new HashSet<>();
        preferenceSet.add(new Preference());
        IPreferenceRepository preferenceRepository = mock(IPreferenceRepository.class);
        when(preferenceRepository.findAllByLocationId(Mockito.<Integer>any())).thenReturn(preferenceSet);
        PreferencesService preferencesService = new PreferencesService(preferenceRepository);
        ILocationRepository iLocationRepository = mock(ILocationRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        IReviewRepository reviewRepository = mock(IReviewRepository.class);
        PreferencesService preferencesService2 = new PreferencesService(mock(IPreferenceRepository.class));
        LocationImagesService locationImagesService = new LocationImagesService(mock(ILocationImagesRepository.class));
        IPreferenceRepository preferenceRepository2 = mock(IPreferenceRepository.class);
        ILocationImagesRepository locationImagesRepository = mock(ILocationImagesRepository.class);
        LocationService locationService = new LocationService(iLocationRepository, userRepository, reviewRepository,
                preferencesService2, locationImagesService, preferenceRepository2, locationImagesRepository,
                new EventService(mock(IEventRepository.class)));

        ReviewService reviewService = new ReviewService(mock(IReviewRepository.class), mock(ILocationRepository.class),
                mock(UserRepository.class));

        (new LocationController(locationService, reviewService, new EventService(mock(IEventRepository.class)),
                preferencesService)).getPreference(1);
    }

    /**
     * Method under test: {@link LocationController#getPreference(Integer)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetPreference3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Integer.intValue()" because the return value of "com.uok.tripplanner.locationService.entity.Location.getId()" is null
        //       at com.uok.tripplanner.locationService.service.PreferencesService.getPreference(PreferencesService.java:40)
        //       at com.uok.tripplanner.locationService.controller.LocationController.getPreference(LocationController.java:75)
        //   See https://diff.blue/R013 to resolve this issue.

        Preference preference = new Preference();
        preference.setLocation(new Location());

        HashSet<Preference> preferenceSet = new HashSet<>();
        preferenceSet.add(preference);
        IPreferenceRepository preferenceRepository = mock(IPreferenceRepository.class);
        when(preferenceRepository.findAllByLocationId(Mockito.<Integer>any())).thenReturn(preferenceSet);
        PreferencesService preferencesService = new PreferencesService(preferenceRepository);
        ILocationRepository iLocationRepository = mock(ILocationRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        IReviewRepository reviewRepository = mock(IReviewRepository.class);
        PreferencesService preferencesService2 = new PreferencesService(mock(IPreferenceRepository.class));
        LocationImagesService locationImagesService = new LocationImagesService(mock(ILocationImagesRepository.class));
        IPreferenceRepository preferenceRepository2 = mock(IPreferenceRepository.class);
        ILocationImagesRepository locationImagesRepository = mock(ILocationImagesRepository.class);
        LocationService locationService = new LocationService(iLocationRepository, userRepository, reviewRepository,
                preferencesService2, locationImagesService, preferenceRepository2, locationImagesRepository,
                new EventService(mock(IEventRepository.class)));

        ReviewService reviewService = new ReviewService(mock(IReviewRepository.class), mock(ILocationRepository.class),
                mock(UserRepository.class));

        (new LocationController(locationService, reviewService, new EventService(mock(IEventRepository.class)),
                preferencesService)).getPreference(1);
    }

    /**
     * Method under test: {@link LocationController#getPreference(Integer)}
     */
    @Test
    void testGetPreference4() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        Location location = new Location();
        location.setId(1);

        Preference preference = new Preference();
        preference.setLocation(location);

        HashSet<Preference> preferenceSet = new HashSet<>();
        preferenceSet.add(preference);
        IPreferenceRepository preferenceRepository = mock(IPreferenceRepository.class);
        when(preferenceRepository.findAllByLocationId(Mockito.<Integer>any())).thenReturn(preferenceSet);
        PreferencesService preferencesService = new PreferencesService(preferenceRepository);
        ILocationRepository iLocationRepository = mock(ILocationRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        IReviewRepository reviewRepository = mock(IReviewRepository.class);
        PreferencesService preferencesService2 = new PreferencesService(mock(IPreferenceRepository.class));
        LocationImagesService locationImagesService = new LocationImagesService(mock(ILocationImagesRepository.class));
        IPreferenceRepository preferenceRepository2 = mock(IPreferenceRepository.class);
        ILocationImagesRepository locationImagesRepository = mock(ILocationImagesRepository.class);
        LocationService locationService = new LocationService(iLocationRepository, userRepository, reviewRepository,
                preferencesService2, locationImagesService, preferenceRepository2, locationImagesRepository,
                new EventService(mock(IEventRepository.class)));

        ReviewService reviewService = new ReviewService(mock(IReviewRepository.class), mock(ILocationRepository.class),
                mock(UserRepository.class));

        List<PreferencesResponseDto> actualPreference = (new LocationController(locationService, reviewService,
                new EventService(mock(IEventRepository.class)), preferencesService)).getPreference(1);
        assertEquals(1, actualPreference.size());
        PreferencesResponseDto getResult = actualPreference.get(0);
        assertNull(getResult.getId());
        assertNull(getResult.getPreference());
        verify(preferenceRepository).findAllByLocationId(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link LocationController#getPreference(Integer)}
     */
    @Test
    void testGetPreference5() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        Location location = mock(Location.class);
        when(location.getId()).thenReturn(1);

        Preference preference = new Preference();
        preference.setLocation(location);

        HashSet<Preference> preferenceSet = new HashSet<>();
        preferenceSet.add(preference);
        IPreferenceRepository preferenceRepository = mock(IPreferenceRepository.class);
        when(preferenceRepository.findAllByLocationId(Mockito.<Integer>any())).thenReturn(preferenceSet);
        PreferencesService preferencesService = new PreferencesService(preferenceRepository);
        ILocationRepository iLocationRepository = mock(ILocationRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        IReviewRepository reviewRepository = mock(IReviewRepository.class);
        PreferencesService preferencesService2 = new PreferencesService(mock(IPreferenceRepository.class));
        LocationImagesService locationImagesService = new LocationImagesService(mock(ILocationImagesRepository.class));
        IPreferenceRepository preferenceRepository2 = mock(IPreferenceRepository.class);
        ILocationImagesRepository locationImagesRepository = mock(ILocationImagesRepository.class);
        LocationService locationService = new LocationService(iLocationRepository, userRepository, reviewRepository,
                preferencesService2, locationImagesService, preferenceRepository2, locationImagesRepository,
                new EventService(mock(IEventRepository.class)));

        ReviewService reviewService = new ReviewService(mock(IReviewRepository.class), mock(ILocationRepository.class),
                mock(UserRepository.class));

        List<PreferencesResponseDto> actualPreference = (new LocationController(locationService, reviewService,
                new EventService(mock(IEventRepository.class)), preferencesService)).getPreference(1);
        assertEquals(1, actualPreference.size());
        PreferencesResponseDto getResult = actualPreference.get(0);
        assertNull(getResult.getId());
        assertNull(getResult.getPreference());
        verify(preferenceRepository).findAllByLocationId(Mockito.<Integer>any());
        verify(location).getId();
    }

    /**
     * Method under test: {@link LocationController#getPreference(Integer)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetPreference6() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.lang.Integer.intValue()" because the return value of "com.uok.tripplanner.locationService.entity.Location.getId()" is null
        //       at com.uok.tripplanner.locationService.service.PreferencesService.getPreference(PreferencesService.java:40)
        //       at com.uok.tripplanner.locationService.controller.LocationController.getPreference(LocationController.java:75)
        //   See https://diff.blue/R013 to resolve this issue.

        Location location = mock(Location.class);
        when(location.getId()).thenReturn(1);
        Preference preference = mock(Preference.class);
        when(preference.getLocation()).thenReturn(new Location());
        doNothing().when(preference).setLocation(Mockito.<Location>any());
        preference.setLocation(location);

        HashSet<Preference> preferenceSet = new HashSet<>();
        preferenceSet.add(preference);
        IPreferenceRepository preferenceRepository = mock(IPreferenceRepository.class);
        when(preferenceRepository.findAllByLocationId(Mockito.<Integer>any())).thenReturn(preferenceSet);
        PreferencesService preferencesService = new PreferencesService(preferenceRepository);
        ILocationRepository iLocationRepository = mock(ILocationRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        IReviewRepository reviewRepository = mock(IReviewRepository.class);
        PreferencesService preferencesService2 = new PreferencesService(mock(IPreferenceRepository.class));
        LocationImagesService locationImagesService = new LocationImagesService(mock(ILocationImagesRepository.class));
        IPreferenceRepository preferenceRepository2 = mock(IPreferenceRepository.class);
        ILocationImagesRepository locationImagesRepository = mock(ILocationImagesRepository.class);
        LocationService locationService = new LocationService(iLocationRepository, userRepository, reviewRepository,
                preferencesService2, locationImagesService, preferenceRepository2, locationImagesRepository,
                new EventService(mock(IEventRepository.class)));

        ReviewService reviewService = new ReviewService(mock(IReviewRepository.class), mock(ILocationRepository.class),
                mock(UserRepository.class));

        (new LocationController(locationService, reviewService, new EventService(mock(IEventRepository.class)),
                preferencesService)).getPreference(1);
    }

    /**
     * Method under test: {@link LocationController#getPreference(Integer)}
     */
    @Test
    void testGetPreference7() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        Location location = new Location();
        location.setId(1);
        Preference preference = mock(Preference.class);
        when(preference.getId()).thenReturn(1);
        when(preference.getPreference()).thenReturn("Preference");
        when(preference.getLocation()).thenReturn(location);
        doNothing().when(preference).setLocation(Mockito.<Location>any());
        preference.setLocation(mock(Location.class));

        HashSet<Preference> preferenceSet = new HashSet<>();
        preferenceSet.add(preference);
        IPreferenceRepository preferenceRepository = mock(IPreferenceRepository.class);
        when(preferenceRepository.findAllByLocationId(Mockito.<Integer>any())).thenReturn(preferenceSet);
        PreferencesService preferencesService = new PreferencesService(preferenceRepository);
        ILocationRepository iLocationRepository = mock(ILocationRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        IReviewRepository reviewRepository = mock(IReviewRepository.class);
        PreferencesService preferencesService2 = new PreferencesService(mock(IPreferenceRepository.class));
        LocationImagesService locationImagesService = new LocationImagesService(mock(ILocationImagesRepository.class));
        IPreferenceRepository preferenceRepository2 = mock(IPreferenceRepository.class);
        ILocationImagesRepository locationImagesRepository = mock(ILocationImagesRepository.class);
        LocationService locationService = new LocationService(iLocationRepository, userRepository, reviewRepository,
                preferencesService2, locationImagesService, preferenceRepository2, locationImagesRepository,
                new EventService(mock(IEventRepository.class)));

        ReviewService reviewService = new ReviewService(mock(IReviewRepository.class), mock(ILocationRepository.class),
                mock(UserRepository.class));

        List<PreferencesResponseDto> actualPreference = (new LocationController(locationService, reviewService,
                new EventService(mock(IEventRepository.class)), preferencesService)).getPreference(1);
        assertEquals(1, actualPreference.size());
        PreferencesResponseDto getResult = actualPreference.get(0);
        assertEquals(1, getResult.getId().intValue());
        assertEquals("Preference", getResult.getPreference());
        verify(preferenceRepository).findAllByLocationId(Mockito.<Integer>any());
        verify(preference).getLocation();
        verify(preference).getId();
        verify(preference).getPreference();
        verify(preference).setLocation(Mockito.<Location>any());
    }

    /**
     * Method under test: {@link LocationController#getPreference(Integer)}
     */
    @Test
    void testGetPreference8() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        Location location = mock(Location.class);
        when(location.getId()).thenReturn(1);
        Location location2 = mock(Location.class);
        when(location2.getId()).thenReturn(-1);
        doNothing().when(location2).setId(Mockito.<Integer>any());
        location2.setId(1);
        Preference preference = mock(Preference.class);
        when(preference.getLocation()).thenReturn(location2);
        doNothing().when(preference).setLocation(Mockito.<Location>any());
        preference.setLocation(location);

        HashSet<Preference> preferenceSet = new HashSet<>();
        preferenceSet.add(preference);
        IPreferenceRepository preferenceRepository = mock(IPreferenceRepository.class);
        when(preferenceRepository.findAllByLocationId(Mockito.<Integer>any())).thenReturn(preferenceSet);
        PreferencesService preferencesService = new PreferencesService(preferenceRepository);
        ILocationRepository iLocationRepository = mock(ILocationRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        IReviewRepository reviewRepository = mock(IReviewRepository.class);
        PreferencesService preferencesService2 = new PreferencesService(mock(IPreferenceRepository.class));
        LocationImagesService locationImagesService = new LocationImagesService(mock(ILocationImagesRepository.class));
        IPreferenceRepository preferenceRepository2 = mock(IPreferenceRepository.class);
        ILocationImagesRepository locationImagesRepository = mock(ILocationImagesRepository.class);
        LocationService locationService = new LocationService(iLocationRepository, userRepository, reviewRepository,
                preferencesService2, locationImagesService, preferenceRepository2, locationImagesRepository,
                new EventService(mock(IEventRepository.class)));

        ReviewService reviewService = new ReviewService(mock(IReviewRepository.class), mock(ILocationRepository.class),
                mock(UserRepository.class));

        assertTrue((new LocationController(locationService, reviewService, new EventService(mock(IEventRepository.class)),
                preferencesService)).getPreference(1).isEmpty());
        verify(preferenceRepository).findAllByLocationId(Mockito.<Integer>any());
        verify(preference).getLocation();
        verify(preference).setLocation(Mockito.<Location>any());
        verify(location2).getId();
        verify(location2).setId(Mockito.<Integer>any());
    }
}

