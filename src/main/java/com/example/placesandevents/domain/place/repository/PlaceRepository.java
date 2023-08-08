package com.example.placesandevents.domain.place.repository;

import com.example.placesandevents.domain.place.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    Place findPlaceByName(String name);
    List<Place> findAllByOverallRatingGreaterThanEqual(Double overallRating);
    List<Place> findAllByIdIn(List<Long> ids);
}
