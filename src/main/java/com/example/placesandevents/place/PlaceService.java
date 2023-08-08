package com.example.placesandevents.place;

import com.example.placesandevents.commons.dto.OperationResponseDTO;
import com.example.placesandevents.domain.place.Place;
import com.example.placesandevents.domain.place.PlaceType;
import com.example.placesandevents.domain.place.repository.PlaceRepository;
import com.example.placesandevents.domain.review.Review;
import com.example.placesandevents.domain.review.repository.ReviewRepository;
import com.example.placesandevents.exception.customexceptions.BadRequest;
import com.example.placesandevents.place.dto.PlaceRatingRequestDTO;
import com.example.placesandevents.place.dto.PlaceRequestDTO;
import com.example.placesandevents.place.dto.PlaceResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.placesandevents.commons.ResponseHelper.buildSuccessResponse;
import static com.example.placesandevents.place.PlaceDTOMapper.mapPlaceToResponse;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final ReviewRepository reviewRepository;

    public OperationResponseDTO crateNewPlace(PlaceRequestDTO request) {
        Place place = Place.builder()
                .name(request.getName())
                .address(request.getAddress())
                .description(request.getDescription())
                .placeType(PlaceType.valueOf(request.getPlaceType()))
                .build();
        placeRepository.save(place);
        return buildSuccessResponse("saved new place");
    }

    public List<PlaceResponseDTO> findAllPlaces() {
        return mapPlaceToResponse(placeRepository.findAll());
    }

    public List<PlaceResponseDTO> findAllPlacesByOverallRating(PlaceRatingRequestDTO ratingRequest) {
        Double overallRating = ratingRequest.getRating();
        if (overallRating > 10.0 || overallRating < 0) {
            throw new BadRequest("Invalid data");
        }
        return mapPlaceToResponse(placeRepository.findAllByOverallRatingGreaterThanEqual(overallRating));
    }

    public void updatePlacesOverallScore() {
        List<Place> collect = placeRepository.findAll()
                .stream()
                .map(this::calculateOverallScoreForPlaceAndUpdate)
                .toList();

        placeRepository.saveAll(collect);
    }

    private Place calculateOverallScoreForPlaceAndUpdate(Place place) {
        List<Review> allByPlaceId = reviewRepository.findAllByPlaceId(place.getId());
        if (allByPlaceId.size() != 0) {
            Double sum = allByPlaceId.stream()
                    .map(Review::getRating)
                    .mapToDouble(Integer::doubleValue)
                    .sum();

            Integer amount = allByPlaceId.size();
            Double overallRating = (sum / amount);
            place.setOverallRating(overallRating);
        }
        return place;
    }
}
