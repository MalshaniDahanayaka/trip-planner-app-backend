package com.uok.tripplanner.authservice.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.uok.tripplanner.authservice.config.JwtService;
import com.uok.tripplanner.authservice.token.TokenRepository;
import com.uok.tripplanner.authservice.user.Role;
import com.uok.tripplanner.authservice.user.User;
import com.uok.tripplanner.authservice.user.UserRepository;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Optional;

import org.apache.catalina.connector.Response;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.DelegatingServletInputStream;
import org.springframework.mock.web.MockHttpServletMapping;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.access.intercept.RunAsImplAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class AuthenticationControllerTest {

    /**
     * Method under test: {@link AuthenticationController#register(RegisterRequest)}
     */
    @Test
    void testRegister2() {

        UserRepository repository = mock(UserRepository.class);
        when(repository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(new User()));

        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        TokenRepository tokenRepository = mock(TokenRepository.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        AuthenticationController authenticationController = new AuthenticationController(new AuthenticationService(
                repository, tokenRepository, passwordEncoder, new JwtService(), authenticationManager));
        ResponseEntity<String> actualRegisterResult = authenticationController
                .register(new RegisterRequest("Jane", "Doe", "jane.doe@example.org", "iloveyou", Role.USER));
        assertEquals("User already exists", actualRegisterResult.getBody());
        assertEquals(200, actualRegisterResult.getStatusCodeValue());
        assertTrue(actualRegisterResult.getHeaders().isEmpty());
        verify(repository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AuthenticationController#register(RegisterRequest)}
     */
    @Test
    void testRegister3() {

        UserRepository repository = mock(UserRepository.class);
        when(repository.save(Mockito.<User>any())).thenReturn(new User());
        when(repository.findByEmail(Mockito.<String>any())).thenReturn(Optional.empty());

        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        TokenRepository tokenRepository = mock(TokenRepository.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        AuthenticationController authenticationController = new AuthenticationController(new AuthenticationService(
                repository, tokenRepository, passwordEncoder, new JwtService(), authenticationManager));
        ResponseEntity<String> actualRegisterResult = authenticationController
                .register(new RegisterRequest("Jane", "Doe", "jane.doe@example.org", "iloveyou", Role.USER));
        assertEquals("User registered successfully", actualRegisterResult.getBody());
        assertEquals(200, actualRegisterResult.getStatusCodeValue());
        assertTrue(actualRegisterResult.getHeaders().isEmpty());
        verify(repository).save(Mockito.<User>any());
        verify(repository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AuthenticationController#register(RegisterRequest)}
     */
    @Test
    void testRegister5() {

        AuthenticationService service = mock(AuthenticationService.class);
        when(service.register(Mockito.<RegisterRequest>any())).thenReturn("Register");
        AuthenticationController authenticationController = new AuthenticationController(service);
        ResponseEntity<String> actualRegisterResult = authenticationController
                .register(new RegisterRequest("Jane", "Doe", "jane.doe@example.org", "iloveyou", Role.USER));
        assertEquals("Register", actualRegisterResult.getBody());
        assertEquals(200, actualRegisterResult.getStatusCodeValue());
        assertTrue(actualRegisterResult.getHeaders().isEmpty());
        verify(service).register(Mockito.<RegisterRequest>any());
    }

    /**
     * Method under test: {@link AuthenticationController#authenticate(AuthenticationRequest)}
     */
    @Test
    void testAuthenticate3() {

        AuthenticationService service = mock(AuthenticationService.class);
        when(service.authenticate(Mockito.<AuthenticationRequest>any())).thenReturn(new AuthenticationResponse());
        AuthenticationController authenticationController = new AuthenticationController(service);
        ResponseEntity<AuthenticationResponse> actualAuthenticateResult = authenticationController
                .authenticate(new AuthenticationRequest("jane.doe@example.org", "iloveyou"));
        assertTrue(actualAuthenticateResult.hasBody());
        assertTrue(actualAuthenticateResult.getHeaders().isEmpty());
        assertEquals(200, actualAuthenticateResult.getStatusCodeValue());
        verify(service).authenticate(Mockito.<AuthenticationRequest>any());
    }

    /**
     * Method under test: {@link AuthenticationController#refreshToken(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testRefreshToken2() throws IOException {

        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        UserRepository repository = mock(UserRepository.class);
        TokenRepository tokenRepository = mock(TokenRepository.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        AuthenticationController authenticationController = new AuthenticationController(new AuthenticationService(
                repository, tokenRepository, passwordEncoder, new JwtService(), authenticationManager));
        MockHttpServletRequest request = new MockHttpServletRequest();
        authenticationController.refreshToken(request, new Response());
        assertFalse(request.isAsyncStarted());
        assertTrue(request.isActive());
        assertTrue(request.getSession() instanceof MockHttpSession);
        assertEquals("", request.getServletPath());
        assertEquals(80, request.getServerPort());
        assertEquals("localhost", request.getServerName());
        assertEquals("http", request.getScheme());
        assertEquals("", request.getRequestURI());
        assertEquals(80, request.getRemotePort());
        assertEquals("localhost", request.getRemoteHost());
        assertEquals("HTTP/1.1", request.getProtocol());
        assertEquals("", request.getMethod());
        assertEquals(80, request.getLocalPort());
        assertEquals("localhost", request.getLocalName());
        assertTrue(request.getInputStream() instanceof DelegatingServletInputStream);
        assertTrue(request.getHttpServletMapping() instanceof MockHttpServletMapping);
        assertEquals(DispatcherType.REQUEST, request.getDispatcherType());
        assertEquals("", request.getContextPath());
        assertEquals(-1L, request.getContentLengthLong());
    }

    /**
     * Method under test: {@link AuthenticationController#refreshToken(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testRefreshToken4() throws IOException {

        AuthenticationService service = mock(AuthenticationService.class);
        doNothing().when(service).refreshToken(Mockito.<HttpServletRequest>any(), Mockito.<HttpServletResponse>any());
        AuthenticationController authenticationController = new AuthenticationController(service);
        MockHttpServletRequest request = new MockHttpServletRequest();
        authenticationController.refreshToken(request, new Response());
        verify(service).refreshToken(Mockito.<HttpServletRequest>any(), Mockito.<HttpServletResponse>any());
    }
}

