package com.example.placesandevents.place.dto;

import com.example.placesandevents.domain.review.Review;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExtendedPlaceResponseDTO {

    private String name;
    private String address;
    private String description;
    private String placeType;
    private Set<Review> reviews;
    private Double overallRating;
}
