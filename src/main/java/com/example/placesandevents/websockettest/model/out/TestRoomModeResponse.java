package com.example.placesandevents.websockettest.model.out;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class TestRoomModeResponse extends EventResponse {

    private String mode;
}
