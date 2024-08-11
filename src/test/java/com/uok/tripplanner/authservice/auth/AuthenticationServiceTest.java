package com.uok.tripplanner.authservice.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.uok.tripplanner.authservice.config.JwtService;
import com.uok.tripplanner.authservice.token.Token;
import com.uok.tripplanner.authservice.token.TokenRepository;
import com.uok.tripplanner.authservice.user.Role;
import com.uok.tripplanner.authservice.user.User;
import com.uok.tripplanner.authservice.user.UserRepository;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Optional;

import org.apache.catalina.connector.Response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.DelegatingServletInputStream;
import org.springframework.mock.web.MockHttpServletMapping;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuthenticationService.class})
@ExtendWith(SpringExtension.class)
class AuthenticationServiceTest {
    @MockBean
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationService authenticationService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private TokenRepository tokenRepository;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link AuthenticationService#register(RegisterRequest)}
     */
    @Test
    void testRegister() {
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(new User()));
        assertEquals("User already exists", authenticationService
                .register(new RegisterRequest("Jane", "Doe", "jane.doe@example.org", "iloveyou", Role.USER)));
        verify(userRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AuthenticationService#register(RegisterRequest)}
     */
    @Test
    void testRegister2() {
        when(userRepository.save(Mockito.<User>any())).thenReturn(new User());
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");
        assertEquals("User registered successfully", authenticationService
                .register(new RegisterRequest("Jane", "Doe", "jane.doe@example.org", "iloveyou", Role.USER)));
        verify(userRepository).save(Mockito.<User>any());
        verify(userRepository).findByEmail(Mockito.<String>any());
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
    }

    /**
     * Method under test: {@link AuthenticationService#authenticate(AuthenticationRequest)}
     */
    @Test
    void testAuthenticate2() throws AuthenticationException {
        User user = new User();
        user.setRole(Role.USER);
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        when(tokenRepository.save(Mockito.<Token>any())).thenReturn(new Token());
        when(tokenRepository.findAllValidTokenByUser(Mockito.<Integer>any())).thenReturn(new ArrayList<>());
        when(jwtService.generateRefreshToken(Mockito.<UserDetails>any())).thenReturn("ABC123");
        when(jwtService.generateToken(Mockito.<UserDetails>any())).thenReturn("ABC123");
        when(authenticationManager.authenticate(Mockito.<Authentication>any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        AuthenticationResponse actualAuthenticateResult = authenticationService
                .authenticate(new AuthenticationRequest("jane.doe@example.org", "iloveyou"));
        assertEquals("ABC123", actualAuthenticateResult.getAccessToken());
        assertEquals(Role.USER, actualAuthenticateResult.getRole());
        assertEquals("ABC123", actualAuthenticateResult.getRefreshToken());
        assertTrue(actualAuthenticateResult.getPermission().isEmpty());
        assertNull(actualAuthenticateResult.getLastName());
        assertNull(actualAuthenticateResult.getFirstName());
        assertNull(actualAuthenticateResult.getEmail());
        verify(userRepository).findByEmail(Mockito.<String>any());
        verify(tokenRepository).save(Mockito.<Token>any());
        verify(tokenRepository).findAllValidTokenByUser(Mockito.<Integer>any());
        verify(jwtService).generateRefreshToken(Mockito.<UserDetails>any());
        verify(jwtService).generateToken(Mockito.<UserDetails>any());
        verify(authenticationManager).authenticate(Mockito.<Authentication>any());
    }

    /**
     * Method under test: {@link AuthenticationService#authenticate(AuthenticationRequest)}
     */
    @Test
    void testAuthenticate3() throws AuthenticationException {
        User user = mock(User.class);
        when(user.getRole()).thenReturn(Role.USER);
        when(user.getId()).thenReturn(1);
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        when(user.getFirstname()).thenReturn("Jane");
        when(user.getLastname()).thenReturn("Doe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        when(tokenRepository.save(Mockito.<Token>any())).thenReturn(new Token());
        when(tokenRepository.findAllValidTokenByUser(Mockito.<Integer>any())).thenReturn(new ArrayList<>());
        when(jwtService.generateRefreshToken(Mockito.<UserDetails>any())).thenReturn("ABC123");
        when(jwtService.generateToken(Mockito.<UserDetails>any())).thenReturn("ABC123");
        when(authenticationManager.authenticate(Mockito.<Authentication>any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        AuthenticationResponse actualAuthenticateResult = authenticationService
                .authenticate(new AuthenticationRequest("jane.doe@example.org", "iloveyou"));
        assertEquals("ABC123", actualAuthenticateResult.getAccessToken());
        assertEquals(Role.USER, actualAuthenticateResult.getRole());
        assertEquals("ABC123", actualAuthenticateResult.getRefreshToken());
        assertTrue(actualAuthenticateResult.getPermission().isEmpty());
        assertEquals("Doe", actualAuthenticateResult.getLastName());
        assertEquals("Jane", actualAuthenticateResult.getFirstName());
        assertEquals("jane.doe@example.org", actualAuthenticateResult.getEmail());
        verify(userRepository).findByEmail(Mockito.<String>any());
        verify(user, atLeast(1)).getRole();
        verify(user).getId();
        verify(user).getEmail();
        verify(user).getFirstname();
        verify(user).getLastname();
        verify(tokenRepository).save(Mockito.<Token>any());
        verify(tokenRepository).findAllValidTokenByUser(Mockito.<Integer>any());
        verify(jwtService).generateRefreshToken(Mockito.<UserDetails>any());
        verify(jwtService).generateToken(Mockito.<UserDetails>any());
        verify(authenticationManager).authenticate(Mockito.<Authentication>any());
    }

    /**
     * Method under test: {@link AuthenticationService#authenticate(AuthenticationRequest)}
     */
    @Test
    void testAuthenticate5() throws AuthenticationException {
        User user = mock(User.class);
        when(user.getRole()).thenReturn(Role.USER);
        when(user.getId()).thenReturn(1);
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        when(user.getFirstname()).thenReturn("Jane");
        when(user.getLastname()).thenReturn("Doe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);

        ArrayList<Token> tokenList = new ArrayList<>();
        tokenList.add(new Token());
        when(tokenRepository.saveAll(Mockito.<Iterable<Token>>any())).thenReturn(new ArrayList<>());
        when(tokenRepository.save(Mockito.<Token>any())).thenReturn(new Token());
        when(tokenRepository.findAllValidTokenByUser(Mockito.<Integer>any())).thenReturn(tokenList);
        when(jwtService.generateRefreshToken(Mockito.<UserDetails>any())).thenReturn("ABC123");
        when(jwtService.generateToken(Mockito.<UserDetails>any())).thenReturn("ABC123");
        when(authenticationManager.authenticate(Mockito.<Authentication>any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        AuthenticationResponse actualAuthenticateResult = authenticationService
                .authenticate(new AuthenticationRequest("jane.doe@example.org", "iloveyou"));
        assertEquals("ABC123", actualAuthenticateResult.getAccessToken());
        assertEquals(Role.USER, actualAuthenticateResult.getRole());
        assertEquals("ABC123", actualAuthenticateResult.getRefreshToken());
        assertTrue(actualAuthenticateResult.getPermission().isEmpty());
        assertEquals("Doe", actualAuthenticateResult.getLastName());
        assertEquals("Jane", actualAuthenticateResult.getFirstName());
        assertEquals("jane.doe@example.org", actualAuthenticateResult.getEmail());
        verify(userRepository).findByEmail(Mockito.<String>any());
        verify(user, atLeast(1)).getRole();
        verify(user).getId();
        verify(user).getEmail();
        verify(user).getFirstname();
        verify(user).getLastname();
        verify(tokenRepository).save(Mockito.<Token>any());
        verify(tokenRepository).findAllValidTokenByUser(Mockito.<Integer>any());
        verify(tokenRepository).saveAll(Mockito.<Iterable<Token>>any());
        verify(jwtService).generateRefreshToken(Mockito.<UserDetails>any());
        verify(jwtService).generateToken(Mockito.<UserDetails>any());
        verify(authenticationManager).authenticate(Mockito.<Authentication>any());
    }

    /**
     * Method under test: {@link AuthenticationService#authenticate(AuthenticationRequest)}
     */
    @Test
    void testAuthenticate6() throws AuthenticationException {
        User user = mock(User.class);
        when(user.getRole()).thenReturn(Role.USER);
        when(user.getId()).thenReturn(1);
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        when(user.getFirstname()).thenReturn("Jane");
        when(user.getLastname()).thenReturn("Doe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);

        ArrayList<Token> tokenList = new ArrayList<>();
        tokenList.add(new Token());
        tokenList.add(new Token());
        when(tokenRepository.saveAll(Mockito.<Iterable<Token>>any())).thenReturn(new ArrayList<>());
        when(tokenRepository.save(Mockito.<Token>any())).thenReturn(new Token());
        when(tokenRepository.findAllValidTokenByUser(Mockito.<Integer>any())).thenReturn(tokenList);
        when(jwtService.generateRefreshToken(Mockito.<UserDetails>any())).thenReturn("ABC123");
        when(jwtService.generateToken(Mockito.<UserDetails>any())).thenReturn("ABC123");
        when(authenticationManager.authenticate(Mockito.<Authentication>any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        AuthenticationResponse actualAuthenticateResult = authenticationService
                .authenticate(new AuthenticationRequest("jane.doe@example.org", "iloveyou"));
        assertEquals("ABC123", actualAuthenticateResult.getAccessToken());
        assertEquals(Role.USER, actualAuthenticateResult.getRole());
        assertEquals("ABC123", actualAuthenticateResult.getRefreshToken());
        assertTrue(actualAuthenticateResult.getPermission().isEmpty());
        assertEquals("Doe", actualAuthenticateResult.getLastName());
        assertEquals("Jane", actualAuthenticateResult.getFirstName());
        assertEquals("jane.doe@example.org", actualAuthenticateResult.getEmail());
        verify(userRepository).findByEmail(Mockito.<String>any());
        verify(user, atLeast(1)).getRole();
        verify(user).getId();
        verify(user).getEmail();
        verify(user).getFirstname();
        verify(user).getLastname();
        verify(tokenRepository).save(Mockito.<Token>any());
        verify(tokenRepository).findAllValidTokenByUser(Mockito.<Integer>any());
        verify(tokenRepository).saveAll(Mockito.<Iterable<Token>>any());
        verify(jwtService).generateRefreshToken(Mockito.<UserDetails>any());
        verify(jwtService).generateToken(Mockito.<UserDetails>any());
        verify(authenticationManager).authenticate(Mockito.<Authentication>any());
    }

    /**
     * Method under test: {@link AuthenticationService#authenticate(AuthenticationRequest)}
     */
    @Test
    void testAuthenticate8() throws AuthenticationException {
        User user = mock(User.class);
        when(user.getRole()).thenReturn(Role.USER);
        when(user.getId()).thenReturn(1);
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        when(user.getFirstname()).thenReturn("Jane");
        when(user.getLastname()).thenReturn("Doe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        Token token = mock(Token.class);
        doNothing().when(token).setExpired(anyBoolean());
        doNothing().when(token).setRevoked(anyBoolean());

        ArrayList<Token> tokenList = new ArrayList<>();
        tokenList.add(token);
        when(tokenRepository.saveAll(Mockito.<Iterable<Token>>any())).thenReturn(new ArrayList<>());
        when(tokenRepository.save(Mockito.<Token>any())).thenReturn(new Token());
        when(tokenRepository.findAllValidTokenByUser(Mockito.<Integer>any())).thenReturn(tokenList);
        when(jwtService.generateRefreshToken(Mockito.<UserDetails>any())).thenReturn("ABC123");
        when(jwtService.generateToken(Mockito.<UserDetails>any())).thenReturn("ABC123");
        when(authenticationManager.authenticate(Mockito.<Authentication>any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));
        AuthenticationResponse actualAuthenticateResult = authenticationService
                .authenticate(new AuthenticationRequest("jane.doe@example.org", "iloveyou"));
        assertEquals("ABC123", actualAuthenticateResult.getAccessToken());
        assertEquals(Role.USER, actualAuthenticateResult.getRole());
        assertEquals("ABC123", actualAuthenticateResult.getRefreshToken());
        assertTrue(actualAuthenticateResult.getPermission().isEmpty());
        assertEquals("Doe", actualAuthenticateResult.getLastName());
        assertEquals("Jane", actualAuthenticateResult.getFirstName());
        assertEquals("jane.doe@example.org", actualAuthenticateResult.getEmail());
        verify(userRepository).findByEmail(Mockito.<String>any());
        verify(user, atLeast(1)).getRole();
        verify(user).getId();
        verify(user).getEmail();
        verify(user).getFirstname();
        verify(user).getLastname();
        verify(tokenRepository).save(Mockito.<Token>any());
        verify(tokenRepository).findAllValidTokenByUser(Mockito.<Integer>any());
        verify(tokenRepository).saveAll(Mockito.<Iterable<Token>>any());
        verify(token).setExpired(anyBoolean());
        verify(token).setRevoked(anyBoolean());
        verify(jwtService).generateRefreshToken(Mockito.<UserDetails>any());
        verify(jwtService).generateToken(Mockito.<UserDetails>any());
        verify(authenticationManager).authenticate(Mockito.<Authentication>any());
    }

    /**
     * Method under test: {@link AuthenticationService#refreshToken(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testRefreshToken() throws IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        authenticationService.refreshToken(request, new Response());
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
     * Method under test: {@link AuthenticationService#refreshToken(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testRefreshToken3() throws IOException {
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.<String>any())).thenReturn("https://example.org/example");
        authenticationService.refreshToken(request, new Response());
        verify(request).getHeader(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AuthenticationService#refreshToken(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testRefreshToken12() throws IOException {
        User user = mock(User.class);
        when(user.getId()).thenReturn(1);
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(ofResult);
        Token token = mock(Token.class);
        doNothing().when(token).setExpired(anyBoolean());
        doNothing().when(token).setRevoked(anyBoolean());

        ArrayList<Token> tokenList = new ArrayList<>();
        tokenList.add(token);
        when(tokenRepository.saveAll(Mockito.<Iterable<Token>>any())).thenReturn(new ArrayList<>());
        when(tokenRepository.save(Mockito.<Token>any())).thenReturn(new Token());
        when(tokenRepository.findAllValidTokenByUser(Mockito.<Integer>any())).thenReturn(tokenList);
        when(jwtService.generateToken(Mockito.<UserDetails>any())).thenReturn("ABC123");
        when(jwtService.isTokenValid(Mockito.<String>any(), Mockito.<UserDetails>any())).thenReturn(true);
        when(jwtService.extractUsername(Mockito.<String>any())).thenReturn("janedoe");
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.<String>any())).thenReturn("Bearer ");
        authenticationService.refreshToken(request, new MockHttpServletResponse());
        verify(userRepository).findByEmail(Mockito.<String>any());
        verify(user).getId();
        verify(tokenRepository).save(Mockito.<Token>any());
        verify(tokenRepository).findAllValidTokenByUser(Mockito.<Integer>any());
        verify(tokenRepository).saveAll(Mockito.<Iterable<Token>>any());
        verify(token).setExpired(anyBoolean());
        verify(token).setRevoked(anyBoolean());
        verify(jwtService).isTokenValid(Mockito.<String>any(), Mockito.<UserDetails>any());
        verify(jwtService).extractUsername(Mockito.<String>any());
        verify(jwtService).generateToken(Mockito.<UserDetails>any());
        verify(request).getHeader(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AuthenticationService#refreshToken(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testRefreshToken13() throws IOException {
        when(userRepository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(mock(User.class)));
        when(jwtService.isTokenValid(Mockito.<String>any(), Mockito.<UserDetails>any())).thenReturn(false);
        when(jwtService.extractUsername(Mockito.<String>any())).thenReturn("janedoe");
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.<String>any())).thenReturn("Bearer ");
        authenticationService.refreshToken(request, mock(HttpServletResponse.class));
        verify(userRepository).findByEmail(Mockito.<String>any());
        verify(jwtService).isTokenValid(Mockito.<String>any(), Mockito.<UserDetails>any());
        verify(jwtService).extractUsername(Mockito.<String>any());
        verify(request).getHeader(Mockito.<String>any());
    }

    /**
     * Method under test: {@link AuthenticationService#refreshToken(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testRefreshToken15() throws IOException {
        when(jwtService.extractUsername(Mockito.<String>any())).thenReturn(null);
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.<String>any())).thenReturn("Bearer ");
        authenticationService.refreshToken(request, mock(HttpServletResponse.class));
        verify(jwtService).extractUsername(Mockito.<String>any());
        verify(request).getHeader(Mockito.<String>any());
    }
}

