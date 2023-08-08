package com.example.placesandevents.websockettest.model.in;

import com.example.placesandevents.websockettest.model.Status;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MessageRequest {

    private String senderName;
    private String roomId;
    private String message;
    private Status status;
}
