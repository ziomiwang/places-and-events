package com.example.placesandevents.hangoverevent.dto;

import com.example.placesandevents.domain.hangoverevent.ChannelType;
import com.example.placesandevents.domain.hangoverevent.EventObject;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SimpleEventObjectDTO {

    private String name;
    private ChannelType channelType;
    private String eventId;

    public static SimpleEventObjectDTO map(EventObject eventObject) {
        return SimpleEventObjectDTO.builder()
                .eventId(eventObject.getEventName())
                .channelType(eventObject.getChannelType())
                .name(eventObject.getEventName())
                .build();
    }
}
