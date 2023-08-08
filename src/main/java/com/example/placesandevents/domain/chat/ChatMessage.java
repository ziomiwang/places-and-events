package com.example.placesandevents.domain.chat;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChatMessage {

    private String senderName;
    private String roomId;
    private String message;
    private LocalDateTime time;
}
