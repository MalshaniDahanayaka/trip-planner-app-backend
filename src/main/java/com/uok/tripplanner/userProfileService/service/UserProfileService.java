package com.uok.tripplanner.userProfileService.service;


import com.uok.tripplanner.authService.user.User;
import com.uok.tripplanner.authService.user.UserRepository;
import com.uok.tripplanner.userProfileService.dto.UserProfile;
import com.uok.tripplanner.userProfileService.dto.UserProfileUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public record UserProfileService(UserRepository userRepository) {

    public Optional<UserProfile> getUserProfile(String email) {

        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()) {
            log.error("User not found for email: {}", email);
            return Optional.empty();
        }
        UserProfile userProfile = UserProfile.builder()
                .firstname(user.get().getFirstname())
                .lastname(user.get().getLastname())
                .email(user.get().getEmail())
                .password(user.get().getPassword())
                .role(user.get().getRole())
                .build();
        return Optional.ofNullable(userProfile);

    }

    public String updateUserProfile(UserProfileUpdate userProfile, String email) {

        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()) {
            log.error("User not found for email: {}", email);
            return "User not found";
        }

        User updateUser = User.builder()
                .id(user.get().getId())
                .firstname(userProfile.getFirstname())
                .lastname(userProfile.getLastname())
                .email(user.get().getEmail())
                .password(user.get().getPassword())
                .role(user.get().getRole())
                .build();

        try {
            userRepository.save(updateUser);
            return "User profile updated successfully";
        } catch (Exception e) {
            log.error("Error occurred while updating user profile", e);
        }
        return "Error occurred while updating user profile";
    }
}
