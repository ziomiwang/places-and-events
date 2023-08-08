package com.example.placesandevents.hangoverevent.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateEventObjectDTO {

    private String name;
    private String channelType;
    private List<String> places;
    private List<String> participants;
}
