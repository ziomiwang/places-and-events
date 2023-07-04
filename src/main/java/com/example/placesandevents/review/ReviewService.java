package com.example.placesandevents.review;

import com.example.placesandevents.commons.dto.OperationResponseDTO;
import com.example.placesandevents.domain.place.repository.PlaceRepository;
import com.example.placesandevents.domain.review.repository.ReviewRepository;
import com.example.placesandevents.domain.user.repository.UserRepository;
import com.example.placesandevents.place.dto.PlaceRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;

    public OperationResponseDTO createNewReviewForPlace(PlaceRequestDTO request, Principal principal) {
        return null;
    }
}
