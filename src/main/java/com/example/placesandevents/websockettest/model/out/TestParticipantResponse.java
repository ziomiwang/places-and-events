package com.example.placesandevents.websockettest.model.out;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class TestParticipantResponse extends EventResponse {

    private String participant;
}
