package com.uok.tripplanner.locationService.service;

import com.uok.tripplanner.locationService.dto.Response.PreferencesResponseDto;
import com.uok.tripplanner.locationService.entity.Location;
import com.uok.tripplanner.locationService.entity.Preference;
import com.uok.tripplanner.locationService.repository.IPreferenceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public record PreferencesService (IPreferenceRepository preferenceRepository){

    public void addLocationPreferences(List<String> preferencesList, Location savedLocation){

    }

    public List<PreferencesResponseDto> getPreferences(){
        List<PreferencesResponseDto> preferencesResponseDtos = new ArrayList<>();
        for(Preference preference : preferenceRepository.findAll()) {
            assert false;
            preferencesResponseDtos.add(PreferencesResponseDto.builder()
                    .id(preference.getId())
                    .preference(preference.getPreference())
                    .build());
        }
        return preferencesResponseDtos;
    }
}
