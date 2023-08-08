package com.example.placesandevents.websockettest.model.out;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {

    private String senderName;
    private String message;
    private String time;
}
