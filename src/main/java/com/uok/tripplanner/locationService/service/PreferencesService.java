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

    public void savePreferences(List<String> preferencesList, Location savedLocation){

        Set<Preference> preferences = new HashSet<>();

        for (String preference : preferencesList) {
            Preference pref = Preference.builder()
                    .preference(preference)
                    .location(savedLocation)
                    .build();
            preferences.add(pref);
        }

        try{
        preferenceRepository.saveAll(preferences);
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
}
