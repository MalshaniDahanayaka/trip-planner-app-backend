package com.uok.tripplanner.locationService.service;

import com.uok.tripplanner.locationService.entity.Location;
import com.uok.tripplanner.locationService.repository.ILocationImagesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record LocationImagesService(ILocationImagesRepository locationImagesRepository) {

    public void addLocationImages(List<String> images, Location location) {

    }


}
