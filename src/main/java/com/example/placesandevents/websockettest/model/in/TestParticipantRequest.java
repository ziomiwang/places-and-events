package com.example.placesandevents.websockettest.model.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestParticipantRequest extends EventRequest{

    private String participant;
}
