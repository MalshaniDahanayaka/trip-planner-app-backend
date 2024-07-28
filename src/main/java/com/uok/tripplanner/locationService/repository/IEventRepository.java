package com.uok.tripplanner.locationService.repository;

import com.uok.tripplanner.locationService.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEventRepository extends JpaRepository<Event, Long> {

    List<Event> findAll();
}
