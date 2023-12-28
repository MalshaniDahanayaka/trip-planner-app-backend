package com.uok.tripplanner.locationService.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "location_image")
public class LocationImage {

    @Id
    @GeneratedValue
    private Integer id;

    private String image;

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;


}
