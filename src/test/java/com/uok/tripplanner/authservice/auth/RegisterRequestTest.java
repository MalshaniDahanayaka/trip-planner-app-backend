package com.uok.tripplanner.authservice.auth;

import com.uok.tripplanner.authservice.user.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterRequestTest {

    private RegisterRequest registerRequest;
    private RegisterRequest registerRequest2;

    @BeforeEach
    void setUp() {
        registerRequest = RegisterRequest.builder()
                .firstname("FirstName")
                .lastname("LastName")
                .email("Email")
                .password("Password")
                .role(Role.ADMIN)
                .build();

        registerRequest2 = RegisterRequest.builder()
                .firstname("FirstName")
                .lastname("LastName")
                .email("Email")
                .password("Password")
                .role(Role.ADMIN)
                .build();
    }

    @Test
    void testAllArgsConstructorAndGetters() {
        RegisterRequest request = new RegisterRequest("FirstName", "LastName", "Email", "Password", Role.ADMIN);
        assertEquals("FirstName", request.getFirstname());
        assertEquals("LastName", request.getLastname());
        assertEquals("Email", request.getEmail());
        assertEquals("Password", request.getPassword());
        assertEquals(Role.ADMIN, request.getRole());
    }

    @Test
    void testSetters() {
        registerRequest.setFirstname("NewFirstName");
        registerRequest.setLastname("NewLastName");
        registerRequest.setEmail("NewEmail");
        registerRequest.setPassword("NewPassword");
        registerRequest.setRole(Role.USER);

        assertEquals("NewFirstName", registerRequest.getFirstname());
        assertEquals("NewLastName", registerRequest.getLastname());
        assertEquals("NewEmail", registerRequest.getEmail());
        assertEquals("NewPassword", registerRequest.getPassword());
        assertEquals(Role.USER, registerRequest.getRole());
    }

    @Test
    void testEqualsAndHashCode() {
        assertEquals(registerRequest, registerRequest2);
        assertEquals(registerRequest.hashCode(), registerRequest2.hashCode());

        RegisterRequest different = RegisterRequest.builder()
                .firstname("Different")
                .lastname("Different")
                .email("Different")
                .password("Different")
                .role(Role.USER)
                .build();

        assertNotEquals(registerRequest, different);
        assertNotEquals(registerRequest.hashCode(), different.hashCode());

        // Test equality with each field different
        assertNotEquals(registerRequest, RegisterRequest.builder().firstname("Different").lastname("LastName").email("Email").password("Password").role(Role.ADMIN).build());
        assertNotEquals(registerRequest, RegisterRequest.builder().firstname("FirstName").lastname("Different").email("Email").password("Password").role(Role.ADMIN).build());
        assertNotEquals(registerRequest, RegisterRequest.builder().firstname("FirstName").lastname("LastName").email("Different").password("Password").role(Role.ADMIN).build());
        assertNotEquals(registerRequest, RegisterRequest.builder().firstname("FirstName").lastname("LastName").email("Email").password("Different").role(Role.ADMIN).build());
        assertNotEquals(registerRequest, RegisterRequest.builder().firstname("FirstName").lastname("LastName").email("Email").password("Password").role(Role.USER).build());
    }

    @Test
    void testEqualsWithNull() {
        assertNotEquals(null, registerRequest);
    }

    @Test
    void testEqualsWithDifferentClass() {
        assertNotEquals(registerRequest, new Object());
    }

    @Test
    void testToString() {
        String expected = "RegisterRequest(firstname=FirstName, lastname=LastName, email=Email, password=Password, role=ADMIN)";
        assertEquals(expected, registerRequest.toString());
    }

    @Test
    void testBuilder() {
        RegisterRequest built = RegisterRequest.builder()
                .firstname("BuilderFirst")
                .lastname("BuilderLast")
                .email("builder@email.com")
                .password("builderPass")
                .role(Role.USER)
                .build();

        assertEquals("BuilderFirst", built.getFirstname());
        assertEquals("BuilderLast", built.getLastname());
        assertEquals("builder@email.com", built.getEmail());
        assertEquals("builderPass", built.getPassword());
        assertEquals(Role.USER, built.getRole());
    }

    @Test
    void testNullFields() {
        RegisterRequest nullRequest = new RegisterRequest(null, null, null, null, null);
        assertNull(nullRequest.getFirstname());
        assertNull(nullRequest.getLastname());
        assertNull(nullRequest.getEmail());
        assertNull(nullRequest.getPassword());
        assertNull(nullRequest.getRole());

        assertNotEquals(nullRequest, registerRequest);
        assertNotNull(nullRequest.toString());
    }
}