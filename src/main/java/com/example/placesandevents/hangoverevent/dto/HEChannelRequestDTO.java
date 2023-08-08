package com.example.placesandevents.hangoverevent.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HEChannelRequestDTO {

    private Long eventId;
    private String channelType;
}
