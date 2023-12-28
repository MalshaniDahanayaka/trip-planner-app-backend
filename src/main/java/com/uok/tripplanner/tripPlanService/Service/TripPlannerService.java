package com.uok.tripplanner.tripPlanService.Service;

import com.uok.tripplanner.authService.user.User;
import com.uok.tripplanner.authService.user.UserRepository;
import com.uok.tripplanner.tripPlanService.Repository.ITripPlannerRepository;
import com.uok.tripplanner.tripPlanService.dto.TripPlannerDto;
import com.uok.tripplanner.tripPlanService.entity.TripPlans;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public record TripPlannerService(ITripPlannerRepository tripPlannerRepository, UserRepository userRepository) {

    public Optional<TripPlannerDto> getTripPlan(Long id) {
        try {
            Optional<TripPlans> tripPlan =  tripPlannerRepository.findById(id);
            if(tripPlan.isEmpty()) {
                return Optional.empty();
            }
            TripPlannerDto tripPlannerDto = TripPlannerDto.builder()
                    .tripPlanId(tripPlan.get().getId())
                    .journeyStartingPlace(tripPlan.get().getJourneyStartingPlace())
                    .startingDate(tripPlan.get().getStartingDate())
                    .startingTime(tripPlan.get().getStartingTime())
                    .journeyEndingPlace(tripPlan.get().getJourneyEndingPlace())
                    .selectEventsAndLocationsList(tripPlan.get().getSelectEventsAndLocationsList())
                    .userEmail(tripPlan.get().getUser().getEmail())
                    .build();
            return Optional.ofNullable(tripPlannerDto);

        } catch (Exception e) {
            log.error("Error occurred while getting trip plan", e);
        }
        return Optional.empty();
    }


    public String saveTripPlan(TripPlannerDto tripPlannerDto) {

        try {
            Optional<User> user = userRepository.findByEmail(tripPlannerDto.getUserEmail());
            if(user.isEmpty()) {
                return "User not found";
            }

            User user1 = User.builder()
                    .id(user.get().getId())
                    .firstname(user.get().getFirstname())
                    .lastname(user.get().getLastname())
                    .email(user.get().getEmail())
                    .password(user.get().getPassword())
                    .role(user.get().getRole())
                    .build();

            TripPlans tripPlans = TripPlans.builder()
                    .journeyStartingPlace(tripPlannerDto.getJourneyStartingPlace())
                    .startingDate(tripPlannerDto.getStartingDate())
                    .startingTime(tripPlannerDto.getStartingTime())
                    .journeyEndingPlace(tripPlannerDto.getJourneyEndingPlace())
                    .selectEventsAndLocationsListJson(null)
                    .user(user1)
                    .build();


            tripPlans.setSelectEventsAndLocationsList(tripPlannerDto.getSelectEventsAndLocationsList());

            log.info("TripPlannerService: saveTripPlan" + tripPlans);
            tripPlannerRepository.save(tripPlans);
            return "Trip plan saved successfully";
        } catch (Exception e) {
            log.error("Error occurred while saving trip plan", e);
        }
        return "Error occurred while saving trip plan";
    }

    public Optional<List<TripPlannerDto>> getAllTripPlans() {

        try{
            List<TripPlans> tripPlans = tripPlannerRepository.findAll();
            if(tripPlans.isEmpty()) {
                return Optional.empty();
            }
            List<TripPlannerDto> tripPlannerDtos = tripPlans.stream().map(tripPlan -> TripPlannerDto.builder()
                    .tripPlanId(tripPlan.getId())
                    .journeyStartingPlace(tripPlan.getJourneyStartingPlace())
                    .startingDate(tripPlan.getStartingDate())
                    .startingTime(tripPlan.getStartingTime())
                    .journeyEndingPlace(tripPlan.getJourneyEndingPlace())
                    .selectEventsAndLocationsList(tripPlan.getSelectEventsAndLocationsList())
                    .userEmail(tripPlan.getUser().getEmail())
                    .build()).toList();
            return Optional.of(tripPlannerDtos);

        } catch (Exception e) {
            log.error("Error occurred while getting trip plan", e);
        }
        return Optional.empty();
    }
}
