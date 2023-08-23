package com.example.placesandevents.websockettest.model.out;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@ToString(callSuper = true)
public class TestMessageResponse extends EventResponse {

    private String senderName;
    private String message;
}
