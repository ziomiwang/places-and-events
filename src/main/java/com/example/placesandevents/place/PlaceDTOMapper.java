package com.example.placesandevents.place;

import com.example.placesandevents.domain.place.Place;
import com.example.placesandevents.place.dto.ExtendedPlaceResponseDTO;
import com.example.placesandevents.place.dto.PlaceRequestDTO;
import com.example.placesandevents.place.dto.PlaceResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class PlaceDTOMapper {

    public static PlaceResponseDTO mapPlaceToResponse(Place place){
        return PlaceResponseDTO.builder()
                .name(place.getName())
                .description(place.getDescription())
                .address(place.getAddress())
                .placeType(place.getPlaceType().name())
                .overallRating(place.getOverallRating())
                .build();
    }

    public static List<PlaceResponseDTO> mapPlaceToResponse(List<Place> places){
        return places.stream()
                .map(PlaceDTOMapper::mapPlaceToResponse)
                .collect(Collectors.toList());
    }

    public static ExtendedPlaceResponseDTO mapPlaceToExtendedResponse(Place place){
        return ExtendedPlaceResponseDTO.builder()
                .name(place.getName())
                .description(place.getDescription())
                .address(place.getAddress())
                .reviews(place.getReviews())
                .placeType(place.getPlaceType().name())
                .overallRating(place.getOverallRating())
                .build();
    }
}
