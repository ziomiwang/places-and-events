package com.example.placesandevents.domain.hangoverevent;

import com.example.placesandevents.domain.chat.ChatMessage;
import com.example.placesandevents.hangoverevent.dto.CreateEventObjectDTO;
import lombok.*;

import java.util.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventObject {

    private String eventId;
    private String eventName;
    private String owner;
    private ChannelType channelType;
    private Set<String> places;
    private Set<String> participants;
    private List<ChatMessage> chatMessages;

    public static EventObject map(CreateEventObjectDTO eventObjectDTO) {
        return EventObject.builder()
                .eventId(eventObjectDTO.getName())
                .eventName(eventObjectDTO.getName())
                .channelType(ChannelType.valueOf(eventObjectDTO.getChannelType()))
                .places(new HashSet<>(eventObjectDTO.getPlaces()))
                .participants(new HashSet<>(eventObjectDTO.getParticipants()))
                .chatMessages(new ArrayList<>())
                .build();
    }

}
