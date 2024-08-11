package com.uok.tripplanner.userProfileService.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.uok.tripplanner.authservice.user.Role;
import com.uok.tripplanner.authservice.user.User;
import com.uok.tripplanner.authservice.user.UserRepository;
import com.uok.tripplanner.userProfileService.dto.UserProfile;
import com.uok.tripplanner.userProfileService.dto.UserProfileUpdate;
import com.uok.tripplanner.userProfileService.service.UserProfileService;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UserProfileControllerTest {
    /**
     * Method under test: {@link UserProfileController#getUserProfile(String)}
     */
    @Test
    void testGetUserProfile() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(new User()));
        Optional<UserProfile> actualUserProfile = (new UserProfileController(new UserProfileService(userRepository)))
                .getUserProfile("jane.doe@example.org");
        assertTrue(actualUserProfile.isPresent());
        UserProfile getResult = actualUserProfile.get();
        assertNull(getResult.getEmail());
        assertNull(getResult.getRole());
        assertNull(getResult.getPassword());
        assertNull(getResult.getLastname());
        assertNull(getResult.getFirstname());
        verify(userRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserProfileController#getUserProfile(String)}
     */
    @Test
    void testGetUserProfile2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        User user = mock(User.class);
        when(user.getRole()).thenReturn(Role.USER);
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        when(user.getFirstname()).thenReturn("Jane");
        when(user.getLastname()).thenReturn("Doe");
        when(user.getPassword()).thenReturn("iloveyou");
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(user));
        Optional<UserProfile> actualUserProfile = (new UserProfileController(new UserProfileService(userRepository)))
                .getUserProfile("jane.doe@example.org");
        assertTrue(actualUserProfile.isPresent());
        UserProfile getResult = actualUserProfile.get();
        assertEquals("jane.doe@example.org", getResult.getEmail());
        assertEquals(Role.USER, getResult.getRole());
        assertEquals("iloveyou", getResult.getPassword());
        assertEquals("Doe", getResult.getLastname());
        assertEquals("Jane", getResult.getFirstname());
        verify(userRepository).findByEmail(Mockito.<String>any());
        verify(user).getRole();
        verify(user).getEmail();
        verify(user).getFirstname();
        verify(user).getLastname();
        verify(user).getPassword();
    }

    /**
     * Method under test: {@link UserProfileController#getUserProfile(String)}
     */
    @Test
    void testGetUserProfile3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        UserRepository userRepository = mock(UserRepository.class);
        Optional<User> emptyResult = Optional.empty();
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(emptyResult);
        Optional<UserProfile> actualUserProfile = (new UserProfileController(new UserProfileService(userRepository)))
                .getUserProfile("jane.doe@example.org");
        assertSame(emptyResult, actualUserProfile);
        assertFalse(actualUserProfile.isPresent());
        verify(userRepository).findByEmail(Mockito.<String>any());
    }
    /**
     * Method under test: {@link UserProfileController#updateUserProfile(UserProfileUpdate, String)}
     */
    @Test
    void testUpdateUserProfile() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.save(Mockito.<User>any())).thenReturn(new User());
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(new User()));
        UserProfileController userProfileController = new UserProfileController(new UserProfileService(userRepository));
        assertEquals("User profile updated successfully",
                userProfileController.updateUserProfile(new UserProfileUpdate("Jane", "Doe"), "jane.doe@example.org"));
        verify(userRepository).save(Mockito.<User>any());
        verify(userRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserProfileController#updateUserProfile(UserProfileUpdate, String)}
     */
    @Test
    void testUpdateUserProfile2() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        User user = mock(User.class);
        when(user.getRole()).thenReturn(Role.USER);
        when(user.getId()).thenReturn(1);
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        when(user.getPassword()).thenReturn("iloveyou");
        Optional<User> ofResult = Optional.of(user);
        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.save(Mockito.<User>any())).thenReturn(new User());
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        UserProfileController userProfileController = new UserProfileController(new UserProfileService(userRepository));
        assertEquals("User profile updated successfully",
                userProfileController.updateUserProfile(new UserProfileUpdate("Jane", "Doe"), "jane.doe@example.org"));
        verify(userRepository).save(Mockito.<User>any());
        verify(userRepository).findByEmail(Mockito.<String>any());
        verify(user).getRole();
        verify(user).getId();
        verify(user).getEmail();
        verify(user).getPassword();
    }

    /**
     * Method under test: {@link UserProfileController#updateUserProfile(UserProfileUpdate, String)}
     */
    @Test
    void testUpdateUserProfile3() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Diffblue AI was unable to find a test

        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.empty());
        UserProfileController userProfileController = new UserProfileController(new UserProfileService(userRepository));
        assertEquals("User not found",
                userProfileController.updateUserProfile(new UserProfileUpdate("Jane", "Doe"), "jane.doe@example.org"));
        verify(userRepository).findByEmail(Mockito.<String>any());
    }

}

