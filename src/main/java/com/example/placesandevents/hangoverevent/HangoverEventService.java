package com.example.placesandevents.hangoverevent;

import com.example.placesandevents.commons.ResponseHelper;
import com.example.placesandevents.commons.dto.OperationResponseDTO;
import com.example.placesandevents.domain.chat.ChatMessage;
import com.example.placesandevents.domain.hangoverevent.ChannelType;
import com.example.placesandevents.domain.hangoverevent.EventObject;
import com.example.placesandevents.domain.hangoverevent.HangoverEvent;
import com.example.placesandevents.domain.hangoverevent.repository.EventObjectRepository;
import com.example.placesandevents.domain.hangoverevent.repository.HangoverEventRepository;
import com.example.placesandevents.domain.place.Place;
import com.example.placesandevents.domain.place.repository.PlaceRepository;
import com.example.placesandevents.domain.user.User;
import com.example.placesandevents.domain.user.repository.UserRepository;
import com.example.placesandevents.exception.customexceptions.BadRequest;
import com.example.placesandevents.hangoverevent.dto.CreateEventObjectDTO;
import com.example.placesandevents.hangoverevent.dto.HEChannelRequestDTO;
import com.example.placesandevents.hangoverevent.dto.HangoverEventRequestDTO;
import com.example.placesandevents.hangoverevent.dto.SimpleEventObjectDTO;
import com.example.placesandevents.websockettest.model.in.*;
import com.example.placesandevents.websockettest.model.out.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.placesandevents.user.PrincipalUtil.getUserFromPrincipal;

@Service
@RequiredArgsConstructor
public class HangoverEventService {

    private final HangoverEventRepository hangoverEventRepository;
    private final EventObjectRepository eventObjectRepository;
    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;

    public EventResponse handleEventRequest(EventRequest eventRequest){
        System.out.println("HANDLING EVENT");
        switch (eventRequest.getEventRequestType()){
            case MESSAGE -> {
                return handleMessageRequest((TestMessageRequest) eventRequest);
            }
            case PARTICIPANT -> {
                return handleParticipantRequest((TestParticipantRequest) eventRequest);
            }
            case PLACE -> {
                return handlePlaceRequest((TestPlaceRequest) eventRequest);
            }
            case MODE -> {
                return handleRoomModeRequest((TestRoomModeRequest) eventRequest);
            }
            default -> throw new BadRequest("Bad request type");
        }
    }

    public OperationResponseDTO createNewEventObject(CreateEventObjectDTO eventObjectRequest, String username) {
        EventObject eventObject = EventObject.map(eventObjectRequest);
        eventObject.getParticipants().add(username);
        eventObject.setOwner(username);
        eventObjectRepository.save(eventObject);
        return ResponseHelper.buildSuccessResponse("success");
    }

    public TestRoomModeResponse handleRoomModeRequest(TestRoomModeRequest roomModeRequest) {
        System.out.println("HANDLING ROOM: " + roomModeRequest);
        eventObjectRepository.updateRoomType(roomModeRequest.getEventId(), roomModeRequest.getMode());
        return TestRoomModeResponse.builder()
                .eventOperationType(roomModeRequest.getEventOperationType())
                .eventRequestType(roomModeRequest.getEventRequestType())
                .mode(roomModeRequest.getMode())
                .build();
    }

    public TestParticipantResponse handleParticipantRequest(TestParticipantRequest participantRequest) {
        System.out.println("HANDLING PARTICIPANT: " + participantRequest);
        if (participantRequest.getEventOperationType().equals(EventOperationType.ADD)) {
            eventObjectRepository.addParticipant(participantRequest.getEventId(), participantRequest.getParticipant());
        } else {
            eventObjectRepository.removeParticipant(participantRequest.getEventId(), participantRequest.getParticipant());
        }
        return TestParticipantResponse.builder()
                .eventOperationType(participantRequest.getEventOperationType())
                .eventRequestType(participantRequest.getEventRequestType())
                .participant(participantRequest.getParticipant())
                .build();
    }

    public TestPlaceResponse handlePlaceRequest(TestPlaceRequest placeRequest) {
        System.out.println("HANDLING PLACE: " + placeRequest);
        if (placeRequest.getEventOperationType().equals(EventOperationType.ADD)) {
            eventObjectRepository.addPlace(placeRequest.getEventId(), placeRequest.getPlace());
        } else {
            eventObjectRepository.removePlace(placeRequest.getEventId(), placeRequest.getPlace());
        }
        return TestPlaceResponse.builder()
                .eventOperationType(placeRequest.getEventOperationType())
                .eventRequestType(placeRequest.getEventRequestType())
                .place(placeRequest.getPlace())
                .build();
    }

    public TestMessageResponse handleMessageRequest(TestMessageRequest message) {
        System.out.println("HANDLING MESSAGE: " + message);
        ChatMessage chatMessage = ChatMessage.builder()
                .message(message.getMessage())
                .senderName(message.getSenderName())
                .roomId(message.getEventId())
                .time(LocalDateTime.now())
                .build();
        eventObjectRepository.updateChatMessages(message.getEventId(), chatMessage);

        return TestMessageResponse.builder()
                .eventOperationType(message.getEventOperationType())
                .eventRequestType(message.getEventRequestType())
                .senderName(chatMessage.getSenderName())
                .message(chatMessage.getMessage())
                .build();
    }

    public List<SimpleEventObjectDTO> getAllEvents(String user) {
        return eventObjectRepository.getAllEvents(user)
                .stream()
                .map(SimpleEventObjectDTO::map)
                .collect(Collectors.toList());
    }

    public EventObject getSingleEventObjectById(String eventId) {
        return eventObjectRepository.getEventObjectById(eventId);
    }

    public OperationResponseDTO createNewHangoverEvent(HangoverEventRequestDTO request, Principal principal) {
        User owner = getUserFromPrincipal(principal);
        List<Place> placeSuggestions = placeRepository.findAllByIdIn(request.getPlaceSuggestionsIds());
        List<User> participants = userRepository.findAllByIdIn(request.getParticipantsIds());

        HangoverEvent hangoverEvent = HangoverEvent.builder()
                .owner(owner)
                .placeSuggestions(new HashSet<>(placeSuggestions))
                .participants(new HashSet<>(participants))
                .channelType(ChannelType.valueOf(request.getChannelType()))
                .name(request.getName())
                .expiration(LocalDateTime.now().plusDays(2))
                .build();

        hangoverEventRepository.save(hangoverEvent);

        return ResponseHelper.buildSuccessResponse("event created");
    }

    private OperationResponseDTO updateEventChannelType(HEChannelRequestDTO request, Principal principal) {
        User owner = getUserFromPrincipal(principal);
        Optional<HangoverEvent> byIdAndOwnerId = hangoverEventRepository.findByIdAndOwnerId(request.getEventId(), owner.getId());

        if (byIdAndOwnerId.isEmpty()) {
            throw new BadRequest("test");
        }
        byIdAndOwnerId.get().setChannelType(ChannelType.valueOf(request.getChannelType()));
        hangoverEventRepository.save(byIdAndOwnerId.get());

        return ResponseHelper.buildSuccessResponse("changed channel type");
    }
}
