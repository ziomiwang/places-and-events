package com.example.placesandevents.domain.place;

import com.example.placesandevents.domain.review.Review;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class Place {

    @Id
    @GeneratedValue(generator = "place_sequence")
    @SequenceGenerator(name = "place_sequence", sequenceName = "place_sequence", allocationSize = 1)
    private Long id;

    private String name;
    private String address;
    private String description;

    @Enumerated(EnumType.STRING)
    private PlaceType placeType;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews;
}
