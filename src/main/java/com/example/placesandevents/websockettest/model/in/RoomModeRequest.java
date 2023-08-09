package com.example.placesandevents.websockettest.model.in;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomModeRequest {

    private String eventId;
    private String mode;
}
