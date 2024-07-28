package com.uok.tripplanner.locationService.service;


import com.uok.tripplanner.locationService.dto.Response.EventResponseDto;
import com.uok.tripplanner.locationService.entity.Event;
import com.uok.tripplanner.locationService.entity.Location;
import com.uok.tripplanner.locationService.repository.IEventRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public record EventService(IEventRepository eventRepository) {

    public void addEvent(List<String> eventList, Location savedLocation){

    }


    public List<EventResponseDto> getEvents(){
        List<EventResponseDto> eventResponseDtos = new ArrayList<>();
        for(Event event : eventRepository.findAll()) {
            assert false;
            eventResponseDtos.add(EventResponseDto.builder()
                    .id(event.getId())
                    .eventName(event.getEventName())
                    .build());
        }
        return eventResponseDtos;
    }
}
