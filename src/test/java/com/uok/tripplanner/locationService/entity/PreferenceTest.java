package com.uok.tripplanner.locationService.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class PreferenceTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Preference#Preference()}
     *   <li>{@link Preference#setId(Integer)}
     *   <li>{@link Preference#setLocation(Location)}
     *   <li>{@link Preference#setPreference(String)}
     *   <li>{@link Preference#getId()}
     *   <li>{@link Preference#getLocation()}
     *   <li>{@link Preference#getPreference()}
     * </ul>
     */
    @Test
    void testConstructor() {
        Preference actualPreference = new Preference();
        actualPreference.setId(1);
        Location location = new Location();
        actualPreference.setLocation(location);
        actualPreference.setPreference("Preference");
        assertEquals(1, actualPreference.getId().intValue());
        assertSame(location, actualPreference.getLocation());
        assertEquals("Preference", actualPreference.getPreference());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Preference#Preference(Integer, String, Location)}
     *   <li>{@link Preference#setId(Integer)}
     *   <li>{@link Preference#setLocation(Location)}
     *   <li>{@link Preference#setPreference(String)}
     *   <li>{@link Preference#getId()}
     *   <li>{@link Preference#getLocation()}
     *   <li>{@link Preference#getPreference()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        Preference actualPreference = new Preference(1, "Preference", new Location());
        actualPreference.setId(1);
        Location location = new Location();
        actualPreference.setLocation(location);
        actualPreference.setPreference("Preference");
        assertEquals(1, actualPreference.getId().intValue());
        assertSame(location, actualPreference.getLocation());
        assertEquals("Preference", actualPreference.getPreference());
    }
}

