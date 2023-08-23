package com.example.placesandevents.websockettest.model.out;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class TestPlaceResponse extends EventResponse {

    private String place;
}
