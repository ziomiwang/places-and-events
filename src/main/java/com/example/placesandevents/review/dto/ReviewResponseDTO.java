package com.example.placesandevents.review.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDTO {

    private Integer rating;
    private String reviewText;
}
