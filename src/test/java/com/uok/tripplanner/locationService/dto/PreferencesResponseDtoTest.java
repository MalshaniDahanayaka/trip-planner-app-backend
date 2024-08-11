package com.uok.tripplanner.locationService.dto;

import com.uok.tripplanner.locationService.dto.Response.PreferencesResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PreferencesResponseDtoTest {

    private PreferencesResponseDto preferencesResponseDto;
    private PreferencesResponseDto preferencesResponseDto2;

    @BeforeEach
    void setUp() {
        preferencesResponseDto = PreferencesResponseDto.builder()
                .id(1)
                .preference("Preference")
                .build();

        preferencesResponseDto2 = PreferencesResponseDto.builder()
                .id(1)
                .preference("Preference")
                .build();
    }

    @Test
    void testId() {
        assertEquals(1, preferencesResponseDto.getId());
    }

    @Test
    void testPreference() {
        assertEquals("Preference", preferencesResponseDto.getPreference());
    }

    @Test
    void testEquals() {
        assertEquals(preferencesResponseDto, preferencesResponseDto2);
    }

    @Test
    void testHashCode() {
        assertEquals(preferencesResponseDto.hashCode(), preferencesResponseDto2.hashCode());
    }

    @Test
    void testToString() {
        String expected = "PreferencesResponseDto(id=1, preference=Preference)";
        assertEquals(expected, preferencesResponseDto.toString());
    }

    @Test
    void testSetter() {
        preferencesResponseDto.setPreference("NewPreference");
        assertEquals("NewPreference", preferencesResponseDto.getPreference());
    }
}