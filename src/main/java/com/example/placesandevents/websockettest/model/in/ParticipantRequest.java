package com.example.placesandevents.websockettest.model.in;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParticipantRequest {

    private String roomId;
    private String participant;
}
