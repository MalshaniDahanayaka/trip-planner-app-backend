package com.uok.tripplanner.authservice.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.uok.tripplanner.authservice.token.Token;
import com.uok.tripplanner.authservice.token.TokenRepository;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;

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
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {LogoutService.class})
@ExtendWith(SpringExtension.class)
class LogoutServiceTest {
    @Autowired
    private LogoutService logoutService;

    @MockBean
    private TokenRepository tokenRepository;

    /**
     * Method under test: {@link LogoutService#logout(HttpServletRequest, HttpServletResponse, Authentication)}
     */
    @Test
    void testLogout() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        Response response = new Response();
        logoutService.logout(request, response, new TestingAuthenticationToken("Principal", "Credentials"));
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
     * Method under test: {@link LogoutService#logout(HttpServletRequest, HttpServletResponse, Authentication)}
     */
    @Test
    void testLogout3() {
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.<String>any())).thenReturn("https://example.org/example");
        Response response = new Response();
        logoutService.logout(request, response, new TestingAuthenticationToken("Principal", "Credentials"));
        verify(request).getHeader(Mockito.<String>any());
    }

    /**
     * Method under test: {@link LogoutService#logout(HttpServletRequest, HttpServletResponse, Authentication)}
     */
    @Test
    void testLogout4() {
        when(tokenRepository.save(Mockito.<Token>any())).thenReturn(new Token());
        when(tokenRepository.findByToken(Mockito.<String>any())).thenReturn(Optional.of(new Token()));
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.<String>any())).thenReturn("Bearer ");
        Response response = new Response();
        logoutService.logout(request, response, new TestingAuthenticationToken("Principal", "Credentials"));
        verify(tokenRepository).save(Mockito.<Token>any());
        verify(tokenRepository).findByToken(Mockito.<String>any());
        verify(request).getHeader(Mockito.<String>any());
    }

    /**
     * Method under test: {@link LogoutService#logout(HttpServletRequest, HttpServletResponse, Authentication)}
     */
    @Test
    void testLogout6() {
        when(tokenRepository.findByToken(Mockito.<String>any())).thenReturn(Optional.empty());
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getHeader(Mockito.<String>any())).thenReturn("Bearer ");
        Response response = new Response();
        logoutService.logout(request, response, new TestingAuthenticationToken("Principal", "Credentials"));
        verify(tokenRepository).findByToken(Mockito.<String>any());
        verify(request).getHeader(Mockito.<String>any());
    }
}

