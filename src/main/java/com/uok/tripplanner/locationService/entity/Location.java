package com.uok.tripplanner.locationService.entity;

import com.uok.tripplanner.authService.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;


import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue
    private Integer id;
    private String locationName;
    private String description;
    private Double latitude;
    private Double longitude;

    @OneToMany(mappedBy = "location")
    private Set<Review> reviews;

    @OneToMany(mappedBy = "location")
    private Set<Preference> preferences;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}
