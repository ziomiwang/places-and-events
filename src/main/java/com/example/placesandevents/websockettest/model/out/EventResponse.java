package com.example.placesandevents.websockettest.model.out;

import com.example.placesandevents.websockettest.model.in.EventOperationType;
import com.example.placesandevents.websockettest.model.in.EventRequestType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public abstract class EventResponse {

    private EventRequestType eventRequestType;
    private EventOperationType eventOperationType;
}
