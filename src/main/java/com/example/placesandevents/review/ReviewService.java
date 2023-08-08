package com.example.placesandevents.review;

import com.example.placesandevents.commons.dto.OperationResponseDTO;
import com.example.placesandevents.domain.place.Place;
import com.example.placesandevents.domain.place.repository.PlaceRepository;
import com.example.placesandevents.domain.review.Review;
import com.example.placesandevents.domain.review.repository.ReviewRepository;
import com.example.placesandevents.domain.user.User;
import com.example.placesandevents.domain.user.repository.UserRepository;
import com.example.placesandevents.exception.customexceptions.BadRequest;
import com.example.placesandevents.review.dto.ReviewRequestDTO;
import com.example.placesandevents.review.dto.ReviewResponseDTO;
import com.example.placesandevents.user.PrincipalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import static com.example.placesandevents.commons.ResponseHelper.buildSuccessResponse;
import static com.example.placesandevents.user.PrincipalUtil.getUserFromPrincipal;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final PlaceRepository placeRepository;

    public OperationResponseDTO createNewReviewForPlace(ReviewRequestDTO request, Principal principal) {
        User user = getUserFromPrincipal(principal);
        Place place = checkIfPlaceExists(request.getPlaceId());

        Review review = Review.builder()
                .place(place)
                .user(user)
                .reviewText(request.getReviewText())
                .rating(request.getRating())
                .build();
        reviewRepository.save(review);

        return buildSuccessResponse("Successfully added new review");
    }

    public List<ReviewResponseDTO> getReviewsByPlace(Long placeId){
        List<Review> allByPlaceId = reviewRepository.findAllByPlaceId(placeId);
        return ReviewDTOMapper.mapReviewToResponse(allByPlaceId);
    }

    public List<ReviewResponseDTO> getReviewsByUser(Principal principal){
        User userFromPrincipal = getUserFromPrincipal(principal);
        return ReviewDTOMapper.mapReviewToResponse(reviewRepository.findAllByUserId(userFromPrincipal.getId()));
    }

    private Place checkIfPlaceExists(Long id){
        Optional<Place> byId = placeRepository.findById(id);
        if (byId.isEmpty()){
            throw new BadRequest("todo");
        }
        return byId.get();
    }
}
