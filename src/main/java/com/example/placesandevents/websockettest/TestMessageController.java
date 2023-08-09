package com.example.placesandevents.websockettest;

import com.example.placesandevents.chat.ChatService;
import com.example.placesandevents.hangoverevent.HangoverEventService;
import com.example.placesandevents.websockettest.model.in.MessageRequest;
import com.example.placesandevents.websockettest.model.in.ParticipantRequest;
import com.example.placesandevents.websockettest.model.in.PlaceRequest;
import com.example.placesandevents.websockettest.model.in.RoomModeRequest;
import com.example.placesandevents.websockettest.model.out.MessageResponse;
import com.example.placesandevents.websockettest.model.out.ParticipantResponse;
import com.example.placesandevents.websockettest.model.out.PlaceResponse;
import com.example.placesandevents.websockettest.model.out.RoomModeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class TestMessageController {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final ChatService chatService;
    private final HangoverEventService eventService;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public MessageRequest receivePublicMessage(@Payload MessageRequest message) {
        System.out.println("new message " + message);
        chatService.processMessage(message);
        return message;
    }

    @MessageMapping("/event-room-mode-request")
    public RoomModeResponse receiveEventRoomModeChange(@Payload RoomModeRequest roomModeRequest) {
        simpMessagingTemplate.convertAndSendToUser(roomModeRequest.getEventId(), "/event-mode", roomModeRequest);
        return eventService.updateEventRoomMode(roomModeRequest);
    }

    @MessageMapping("/event-participant-request")
    public ParticipantResponse receiveParticipantChange(@Payload ParticipantRequest participantRequest) {
        simpMessagingTemplate.convertAndSendToUser(participantRequest.getEventId(), "/event-participant", participantRequest);
        return eventService.updateParticipants(participantRequest);
    }

    @MessageMapping("/event-place-request")
    public PlaceResponse receivePlaceChange(@Payload PlaceRequest placeRequest) {
        simpMessagingTemplate.convertAndSendToUser(placeRequest.getEventId(), "/event-place", placeRequest);
        return eventService.updatePlaces(placeRequest);
    }

    @MessageMapping("/event-message-request")
    public MessageResponse receivePrivateMessage(@Payload MessageRequest message){
        simpMessagingTemplate.convertAndSendToUser(message.getEventId(), "/event-chat", message);
        return eventService.updateChatMessages(message);
    }
}
