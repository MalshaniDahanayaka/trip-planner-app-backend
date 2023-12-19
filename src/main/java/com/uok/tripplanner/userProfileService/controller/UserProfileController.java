package com.uok.tripplanner.userProfileService.controller;


import com.uok.tripplanner.userProfileService.dto.UserProfile;
import com.uok.tripplanner.userProfileService.dto.UserProfileUpdate;
import com.uok.tripplanner.userProfileService.service.UserProfileService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1/user-profile")
public class UserProfileController {

    private final UserProfileService userProfileService;

    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }


    @GetMapping("/{email}")
    public Optional<UserProfile> getUserProfile(@PathVariable("email") String email) {
        return userProfileService.getUserProfile(email);
    }

    @PutMapping("/update/{email}")
    public String updateUserProfile(@RequestBody UserProfileUpdate userProfile, @PathVariable String email) {
        return userProfileService.updateUserProfile(userProfile, email);
    }

}
