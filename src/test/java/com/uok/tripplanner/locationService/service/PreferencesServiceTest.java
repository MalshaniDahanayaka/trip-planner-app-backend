package com.uok.tripplanner.locationService.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.uok.tripplanner.locationService.dto.Response.PreferencesResponseDto;
import com.uok.tripplanner.locationService.entity.Location;
import com.uok.tripplanner.locationService.entity.Preference;
import com.uok.tripplanner.locationService.repository.IPreferenceRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PreferencesService.class})
@ExtendWith(SpringExtension.class)
class PreferencesServiceTest {
    @MockBean
    private IPreferenceRepository iPreferenceRepository;

    @Autowired
    private PreferencesService preferencesService;

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link PreferencesService#PreferencesService(IPreferenceRepository)}
     * </ul>
     */
    @Test
    void testConstructor() {
        IPreferenceRepository preferenceRepository = mock(IPreferenceRepository.class);
        PreferencesService actualPreferencesService = new PreferencesService(preferenceRepository);
        ArrayList<String> preferencesList = new ArrayList<>();
        List<PreferencesResponseDto> preferences = actualPreferencesService.getPreferences();
        assertEquals(preferencesList, preferences);
        assertTrue(preferences.isEmpty());
        assertSame(preferenceRepository, actualPreferencesService.preferenceRepository());
    }

    /**
     * Method under test: {@link PreferencesService#getPreference(int)}
     */
    @Test
    void testGetPreference() {
        when(iPreferenceRepository.findAllByLocationId(Mockito.<Integer>any())).thenReturn(new HashSet<>());
        assertTrue(preferencesService.getPreference(1).isEmpty());
        verify(iPreferenceRepository).findAllByLocationId(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link PreferencesService#getPreference(int)}
     */
    @Test
    void testGetPreference4() {
        Location location = new Location();
        location.setId(1);

        Preference preference = new Preference();
        preference.setLocation(location);

        HashSet<Preference> preferenceSet = new HashSet<>();
        preferenceSet.add(preference);
        when(iPreferenceRepository.findAllByLocationId(Mockito.<Integer>any())).thenReturn(preferenceSet);
        List<PreferencesResponseDto> actualPreference = preferencesService.getPreference(1);
        assertEquals(1, actualPreference.size());
        PreferencesResponseDto getResult = actualPreference.get(0);
        assertNull(getResult.getId());
        assertNull(getResult.getPreference());
        verify(iPreferenceRepository).findAllByLocationId(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link PreferencesService#getPreference(int)}
     */
    @Test
    void testGetPreference5() {
        Location location = mock(Location.class);
        when(location.getId()).thenReturn(-1);

        Preference preference = new Preference();
        preference.setLocation(location);

        HashSet<Preference> preferenceSet = new HashSet<>();
        preferenceSet.add(preference);
        when(iPreferenceRepository.findAllByLocationId(Mockito.<Integer>any())).thenReturn(preferenceSet);
        assertTrue(preferencesService.getPreference(1).isEmpty());
        verify(iPreferenceRepository).findAllByLocationId(Mockito.<Integer>any());
        verify(location).getId();
    }

    /**
     * Method under test: {@link PreferencesService#getPreferences()}
     */
    @Test
    void testGetPreferences() {
        when(iPreferenceRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(preferencesService.getPreferences().isEmpty());
        verify(iPreferenceRepository).findAll();
    }
}

