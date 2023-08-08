package com.example.placesandevents.place.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PlaceRequestDTO {

    private String name;
    private String address;
    private String description;
    private String placeType;
}
