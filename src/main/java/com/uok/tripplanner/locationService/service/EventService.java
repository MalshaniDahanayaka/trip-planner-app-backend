package com.uok.tripplanner.locationService.service;


import com.uok.tripplanner.locationService.entity.Location;
import com.uok.tripplanner.locationService.repository.IEventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record EventService(IEventRepository eventRepository) {

    public void addEvent(List<String> eventList, Location savedLocation){

    }
}
