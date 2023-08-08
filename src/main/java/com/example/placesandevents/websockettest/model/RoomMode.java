package com.example.placesandevents.websockettest.model;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RoomMode {

    private String mode;
    private String receiverName;
}
