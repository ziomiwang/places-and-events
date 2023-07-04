package com.example.placesandevents.domain.review;

import com.example.placesandevents.domain.place.Place;
import com.example.placesandevents.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Review {

    @Id
    @GeneratedValue(generator = "review_sequence")
    @SequenceGenerator(name = "review_sequence", sequenceName = "review_sequence", allocationSize = 1)
    private Long id;
    private Integer rating;
    private String reviewText;

    @ManyToOne
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Place place;
}
