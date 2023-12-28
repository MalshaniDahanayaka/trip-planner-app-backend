package com.uok.tripplanner.locationService.entity;


import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event")
public class Event {

    @Id
    @GeneratedValue
    private Integer id;
    private String eventName;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

}
