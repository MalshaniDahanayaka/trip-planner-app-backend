package com.uok.tripplanner.authservice.auth;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationRequestTest {

    private AuthenticationRequest authenticationRequest;
    private AuthenticationRequest authenticationRequest2;

    @BeforeEach
    void setUp() {
        authenticationRequest = AuthenticationRequest.builder()
                .email("test@example.com")
                .password("password")
                .build();

        authenticationRequest2 = AuthenticationRequest.builder()
                .email("test@example.com")
                .password("password")
                .build();
    }

    @Test
    void testEmail() {
        assertEquals("test@example.com", authenticationRequest.getEmail());
    }

    @Test
    void testPassword() {
        assertEquals("password", authenticationRequest.getPassword());
    }

    @Test
    void testEquals() {
        assertEquals(authenticationRequest, authenticationRequest2);
    }

    @Test
    void testHashCode() {
        assertEquals(authenticationRequest.hashCode(), authenticationRequest2.hashCode());
    }

    @Test
    void testToString() {
        String expected = "AuthenticationRequest(email=test@example.com, password=password)";
        assertEquals(expected, authenticationRequest.toString());
    }

    @Test
    void testSetters() {
        authenticationRequest.setEmail("newEmail@example.com");
        authenticationRequest.setPassword("newPassword");

        assertEquals("newEmail@example.com", authenticationRequest.getEmail());
        assertEquals("newPassword", authenticationRequest.getPassword());
    }

    @Test
    void testNoArgsConstructor() {
        AuthenticationRequest authenticationRequest1 = new AuthenticationRequest();
        assertNotNull(authenticationRequest1);
    }

    @Test
    void testNotEquals() {
        AuthenticationRequest authenticationRequest3 = AuthenticationRequest.builder()
                .email("different@example.com")
                .password("password")
                .build();
        assertNotEquals(authenticationRequest, authenticationRequest3);
    }

    @Test
    void testDifferentHashCode() {
        AuthenticationRequest authenticationRequest3 = AuthenticationRequest.builder()
                .email("different@example.com")
                .password("password")
                .build();
        assertNotEquals(authenticationRequest.hashCode(), authenticationRequest3.hashCode());
    }

    @Test
    void testToStringWithDifferentObject() {
        AuthenticationRequest authenticationRequest3 = AuthenticationRequest.builder()
                .email("different@example.com")
                .password("password")
                .build();
        String expected = "AuthenticationRequest(email=different@example.com, password=password)";
        assertEquals(expected, authenticationRequest3.toString());
    }

    @Test
    void testSettersWithDifferentObject() {
        AuthenticationRequest authenticationRequest3 = new AuthenticationRequest();
        authenticationRequest3.setEmail("different@example.com");
        authenticationRequest3.setPassword("password");

        assertEquals("different@example.com", authenticationRequest3.getEmail());
        assertEquals("password", authenticationRequest3.getPassword());
    }
}