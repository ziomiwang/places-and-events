package com.example.placesandevents.hangoverevent.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HangoverEventRequestDTO {

    private String name;
    private String channelType;
    private List<Long> placeSuggestionsIds;
    private List<Long> participantsIds;
}
