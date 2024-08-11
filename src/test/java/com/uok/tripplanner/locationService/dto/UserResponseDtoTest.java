package com.uok.tripplanner.locationService.dto;

import com.uok.tripplanner.authservice.user.Role;
import com.uok.tripplanner.locationService.dto.Response.UserResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserResponseDtoTest {

    private UserResponseDto userResponseDto;
    private UserResponseDto userResponseDto2;

    @BeforeEach
    void setUp() {
        userResponseDto = new UserResponseDto("FirstName", "LastName", "Email", Role.USER);
        userResponseDto2 = new UserResponseDto("FirstName", "LastName", "Email", Role.USER);
    }

    @Test
    void testFirstname() {
        assertEquals("FirstName", userResponseDto.getFirstname());
    }

    @Test
    void testLastname() {
        assertEquals("LastName", userResponseDto.getLastname());
    }

    @Test
    void testEmail() {
        assertEquals("Email", userResponseDto.getEmail());
    }

    @Test
    void testRole() {
        assertEquals(Role.USER, userResponseDto.getRole());
    }

    @Test
    void testEquals() {
        assertEquals(userResponseDto, userResponseDto2);
    }

    @Test
    void testHashCode() {
        assertEquals(userResponseDto.hashCode(), userResponseDto2.hashCode());
    }

    @Test
    void testToString() {
        String expected = "UserResponseDto(firstname=FirstName, lastname=LastName, email=Email, role=USER)";
        assertEquals(expected, userResponseDto.toString());
    }

    @Test
    void testSetters() {
        userResponseDto.setFirstname("NewFirstName");
        userResponseDto.setLastname("NewLastName");
        userResponseDto.setEmail("NewEmail");
        userResponseDto.setRole(Role.ADMIN);

        assertEquals("NewFirstName", userResponseDto.getFirstname());
        assertEquals("NewLastName", userResponseDto.getLastname());
        assertEquals("NewEmail", userResponseDto.getEmail());
        assertEquals(Role.ADMIN, userResponseDto.getRole());
    }
}