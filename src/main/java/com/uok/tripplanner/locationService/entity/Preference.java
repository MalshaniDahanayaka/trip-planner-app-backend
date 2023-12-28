package com.uok.tripplanner.locationService.entity;

import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "preference")
public class Preference {

    @Id
    @GeneratedValue
    private Integer id;
    private String preference;


    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

}
