package com.uok.tripplanner.authservice.auth;

import com.uok.tripplanner.authservice.user.Permission;
import com.uok.tripplanner.authservice.user.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthenticationResponseTest {

    private AuthenticationResponse authenticationResponse;
    private AuthenticationResponse authenticationResponse2;

    @BeforeEach
    void setUp() {
        Set<Permission> permissions = new HashSet<>();
        permissions.add(Permission.ADMIN_READ);

        authenticationResponse = AuthenticationResponse.builder()
                .accessToken("AccessToken")
                .refreshToken("RefreshToken")
                .email("Email")
                .firstName("FirstName")
                .lastName("LastName")
                .role(Role.ADMIN)
                .permission(permissions)
                .build();

        authenticationResponse2 = AuthenticationResponse.builder()
                .accessToken("AccessToken")
                .refreshToken("RefreshToken")
                .email("Email")
                .firstName("FirstName")
                .lastName("LastName")
                .role(Role.ADMIN)
                .permission(permissions)
                .build();
    }

    @Test
    void testAccessToken() {
        assertEquals("AccessToken", authenticationResponse.getAccessToken());
    }

    @Test
    void testRefreshToken() {
        assertEquals("RefreshToken", authenticationResponse.getRefreshToken());
    }

    @Test
    void testEmail() {
        assertEquals("Email", authenticationResponse.getEmail());
    }

    @Test
    void testFirstName() {
        assertEquals("FirstName", authenticationResponse.getFirstName());
    }

    @Test
    void testLastName() {
        assertEquals("LastName", authenticationResponse.getLastName());
    }

    @Test
    void testRole() {
        assertEquals(Role.ADMIN, authenticationResponse.getRole());
    }

    @Test
    void testPermission() {
        Set<Permission> permissions = new HashSet<>();
        permissions.add(Permission.ADMIN_READ);
        assertEquals(permissions, authenticationResponse.getPermission());
    }

    @Test
    void testEquals() {
        assertEquals(authenticationResponse, authenticationResponse2);
    }

    @Test
    void testHashCode() {
        assertEquals(authenticationResponse.hashCode(), authenticationResponse2.hashCode());
    }

    @Test
    void testToString() {
        String expected = "AuthenticationResponse(accessToken=AccessToken, refreshToken=RefreshToken, email=Email, firstName=FirstName, lastName=LastName, role=ADMIN, permission=[ADMIN_READ])";
        assertEquals(expected, authenticationResponse.toString());
    }

    @Test
    void testSetter() {
        authenticationResponse.setAccessToken("NewAccessToken");
        assertEquals("NewAccessToken", authenticationResponse.getAccessToken());
    }
}