package com.example.placesandevents.place.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlaceResponseDTO {

    private String name;
    private String address;
    private String description;
    private String placeType;
    private Double overallRating;
}
