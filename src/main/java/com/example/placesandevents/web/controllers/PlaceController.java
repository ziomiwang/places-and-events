package com.example.placesandevents.web.controllers;

import com.example.placesandevents.commons.dto.OperationResponseDTO;
import com.example.placesandevents.exception.customexceptions.BadRequest;
import com.example.placesandevents.place.PlaceService;
import com.example.placesandevents.place.dto.PlaceRequestDTO;
import com.example.placesandevents.place.dto.PlaceResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/place")
public class PlaceController {

    private final PlaceService placeService;

    @PostMapping("/create")
    ResponseEntity<OperationResponseDTO> createNewPlace(@RequestBody PlaceRequestDTO request) {
        return ResponseEntity.ok(placeService.crateNewPlace(request));
    }

    @GetMapping("/all")
    ResponseEntity<List<PlaceResponseDTO>> getAllPlaces() {
        throw new BadRequest("lalala");
//        return ResponseEntity.ok(placeService.getAllPlacesByUser());
    }
}
