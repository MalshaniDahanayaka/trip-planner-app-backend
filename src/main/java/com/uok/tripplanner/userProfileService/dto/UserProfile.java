package com.uok.tripplanner.userProfileService.dto;

import com.uok.tripplanner.authService.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;
}
