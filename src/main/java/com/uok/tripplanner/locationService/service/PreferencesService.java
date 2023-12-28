package com.uok.tripplanner.locationService.service;

import com.uok.tripplanner.locationService.entity.Location;
import com.uok.tripplanner.locationService.entity.Preference;
import com.uok.tripplanner.locationService.repository.IPreferenceRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public record PreferencesService (IPreferenceRepository preferenceRepository){

    public void addLocationPreferences(List<String> preferencesList, Location savedLocation){

    }
}
