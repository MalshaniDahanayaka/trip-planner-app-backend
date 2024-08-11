package com.uok.tripplanner.locationService.dto.Response;

import com.uok.tripplanner.authservice.user.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDto {

    private String firstname;
    private String lastname;
    private String email;
    private Role role;
}
