package com.example.placesandevents.place;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class PlaceReviewScheduler {

    private final PlaceService placeService;

    @Scheduled(fixedRate = 1000 * 60 * 60)
    public void updateAllPlacesOverallScore(){
        log.info("Updating overall score of all places");
        placeService.updatePlacesOverallScore();
    }
}
