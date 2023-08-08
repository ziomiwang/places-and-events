package com.example.placesandevents.web.controllers;

import com.example.placesandevents.commons.dto.OperationResponseDTO;
import com.example.placesandevents.review.ReviewService;
import com.example.placesandevents.review.dto.ReviewRequestDTO;
import com.example.placesandevents.review.dto.ReviewResponseDTO;
import com.example.placesandevents.user.PrincipalUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/all/{id}")
    ResponseEntity<List<ReviewResponseDTO>> getPlaceReviews(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getReviewsByPlace(id));
    }

    @GetMapping("/all")
    ResponseEntity<List<ReviewResponseDTO>> getReviewsByUser(Principal principal){
        return ResponseEntity.ok(reviewService.getReviewsByUser(principal));
    }

    @PostMapping("/new")
    ResponseEntity<OperationResponseDTO> addReviewToPlace(@RequestBody ReviewRequestDTO request, Principal principal) {
        return ResponseEntity.ok(reviewService.createNewReviewForPlace(request, principal));
    }
}
