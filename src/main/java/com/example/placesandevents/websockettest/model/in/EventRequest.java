package com.example.placesandevents.websockettest.model.in;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({
        @JsonSubTypes.Type(TestMessageRequest.class),
        @JsonSubTypes.Type(TestPlaceRequest.class),
        @JsonSubTypes.Type(TestRoomModeRequest.class),
        @JsonSubTypes.Type(TestParticipantRequest.class)
})
public abstract class EventRequest{

    private String eventId;
    private EventRequestType eventRequestType;
    private EventOperationType eventOperationType;

}
