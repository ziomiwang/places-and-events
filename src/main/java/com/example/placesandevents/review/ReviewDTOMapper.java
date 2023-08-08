package com.example.placesandevents.review;

import com.example.placesandevents.domain.review.Review;
import com.example.placesandevents.review.dto.ReviewResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewDTOMapper {

    public static ReviewResponseDTO mapReviewToResponse(Review review){
        return ReviewResponseDTO.builder()
                .reviewText(review.getReviewText())
                .rating(review.getRating())
                .build();
    }

    public static List<ReviewResponseDTO> mapReviewToResponse(List<Review> reviews) {
        return reviews.stream()
                .map(ReviewDTOMapper::mapReviewToResponse)
                .collect(Collectors.toList());
    }
}
