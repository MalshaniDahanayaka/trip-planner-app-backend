package com.uok.tripplanner.authservice.config;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.uok.tripplanner.authservice.user.User;
import com.uok.tripplanner.authservice.user.UserRepository;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ApplicationConfig.class, AuthenticationConfiguration.class})
@ExtendWith(SpringExtension.class)
class ApplicationConfigTest {
    @Autowired
    private ApplicationConfig applicationConfig;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link ApplicationConfig#userDetailsService()}
     */
    @Test
    void testUserDetailsService() throws UsernameNotFoundException {
        UserRepository repository = mock(UserRepository.class);
        User user = new User();
        when(repository.findByEmail(Mockito.<String>any())).thenReturn(Optional.of(user));
        UserDetails actualLoadUserByUsernameResult = (new ApplicationConfig(repository)).userDetailsService()
                .loadUserByUsername("janedoe");
        verify(repository).findByEmail(Mockito.<String>any());
        assertSame(user, actualLoadUserByUsernameResult);
    }

    /**
     * Method under test: {@link ApplicationConfig#userDetailsService()}
     */
    @Test
    void testUserDetailsService2() throws UsernameNotFoundException {
        UserRepository repository = mock(UserRepository.class);
        when(repository.findByEmail(Mockito.<String>any())).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class,
                () -> (new ApplicationConfig(repository)).userDetailsService().loadUserByUsername("janedoe"));
        verify(repository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ApplicationConfig#userDetailsService()}
     */
    @Test
    void testUserDetailsService3() throws UsernameNotFoundException {
        UserRepository repository = mock(UserRepository.class);
        when(repository.findByEmail(Mockito.<String>any()))
                .thenThrow(new UsernameNotFoundException("User not found fucker"));
        assertThrows(UsernameNotFoundException.class,
                () -> (new ApplicationConfig(repository)).userDetailsService().loadUserByUsername("janedoe"));
        verify(repository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link ApplicationConfig#authenticationProvider()}
     */
    @Test
    void testAuthenticationProvider() {
        assertTrue(applicationConfig.authenticationProvider() instanceof DaoAuthenticationProvider);
    }

    /**
     * Method under test: {@link ApplicationConfig#authenticationManager(AuthenticationConfiguration)}
     */
    @Test
    void testAuthenticationManager() throws Exception {
        assertTrue(applicationConfig.authenticationManager(new AuthenticationConfiguration()) instanceof ProviderManager);
    }

    /**
     * Method under test: {@link ApplicationConfig#passwordEncoder()}
     */
    @Test
    void testPasswordEncoder() {
        assertTrue(applicationConfig.passwordEncoder() instanceof BCryptPasswordEncoder);
    }
}

