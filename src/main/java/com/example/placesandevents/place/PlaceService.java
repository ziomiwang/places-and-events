package com.example.placesandevents.place;

import com.example.placesandevents.commons.dto.OperationResponseDTO;
import com.example.placesandevents.domain.place.Place;
import com.example.placesandevents.domain.place.PlaceType;
import com.example.placesandevents.domain.place.repository.PlaceRepository;
import com.example.placesandevents.place.dto.PlaceRequestDTO;
import com.example.placesandevents.place.dto.PlaceResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.placesandevents.commons.ResponseHelper.buildSuccessResponse;
import static com.example.placesandevents.place.PlaceDTOMapper.mapPlaceToResponse;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    public OperationResponseDTO crateNewPlace(PlaceRequestDTO request){
        Place place = Place.builder()
                .name(request.getName())
                .address(request.getAddress())
                .description(request.getDescription())
                .placeType(PlaceType.valueOf(request.getPlaceType()))
                .build();
        placeRepository.save(place);
        return buildSuccessResponse("saved new place");
    }

    public List<PlaceResponseDTO> getAllPlacesByUser() {
        return mapPlaceToResponse(placeRepository.findAll());
    }
}
