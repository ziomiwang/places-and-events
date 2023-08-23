package com.example.placesandevents.websockettest;

import com.example.placesandevents.hangoverevent.HangoverEventService;
import com.example.placesandevents.websockettest.model.in.EventRequest;
import com.example.placesandevents.websockettest.model.out.EventResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class TestMessageController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final HangoverEventService eventService;

    @MessageMapping("/event-request")
    public EventResponse receiveEventRequest(@Payload EventRequest eventRequest){
        simpMessagingTemplate.convertAndSendToUser(eventRequest.getEventId(), "/event", eventRequest);
        EventResponse eventResponse = eventService.handleEventRequest(eventRequest);
        System.out.println();
        return eventResponse;
    }

//    @MessageMapping("/event-room-mode-request")
//    public RoomModeResponse receiveEventRoomModeChange(@Payload RoomModeRequest roomModeRequest) {
//        simpMessagingTemplate.convertAndSendToUser(roomModeRequest.getEventId(), "/event-mode", roomModeRequest);
//        return eventService.updateEventRoomMode(roomModeRequest);
//    }
//
//    @MessageMapping("/event-participant-request")
//    public ParticipantResponse receiveParticipantChange(@Payload ParticipantRequest participantRequest) {
//        simpMessagingTemplate.convertAndSendToUser(participantRequest.getEventId(), "/event-participant", participantRequest);
//        return eventService.updateParticipants(participantRequest);
//    }
//
//    @MessageMapping("/event-place-request")
//    public PlaceResponse receivePlaceChange(@Payload PlaceRequest placeRequest) {
//        simpMessagingTemplate.convertAndSendToUser(placeRequest.getEventId(), "/event-place", placeRequest);
//        return eventService.updatePlaces(placeRequest);
//    }
//
//    @MessageMapping("/event-message-request")
//    public MessageResponse receivePrivateMessage(@Payload MessageRequest message) {
//        simpMessagingTemplate.convertAndSendToUser(message.getEventId(), "/event-chat", message);
//        return eventService.updateChatMessages(message);
//    }
}
