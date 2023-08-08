package com.example.placesandevents.websockettest.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OutputMessage {

    private String from;
    private String message;
    private String time;
}
