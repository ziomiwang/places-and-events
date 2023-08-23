package com.example.placesandevents.websockettest.model.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class TestMessageRequest extends EventRequest {

    private String senderName;
    private String message;
}
