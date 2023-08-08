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
import com.example.placesandevents.websockettest.model.in.MessageRequest;
import com.example.placesandevents.websockettest.model.in.ParticipantRequest;
import com.example.placesandevents.websockettest.model.in.PlaceRequest;
import com.example.placesandevents.websockettest.model.in.RoomModeRequest;
import com.example.placesandevents.websockettest.model.out.MessageResponse;
import com.example.placesandevents.websockettest.model.out.ParticipantResponse;
import com.example.placesandevents.websockettest.model.out.PlaceResponse;
import com.example.placesandevents.websockettest.model.out.RoomModeResponse;
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

    public OperationResponseDTO createNewEventObject(CreateEventObjectDTO eventObject){
        eventObjectRepository.save(EventObject.map(eventObject));
        return ResponseHelper.buildSuccessResponse("success");
    }

    public RoomModeResponse updateEventRoomMode(RoomModeRequest roomModeRequest){
        eventObjectRepository.updateRoomType(roomModeRequest.getRoomId(), roomModeRequest.getMode());
        return RoomModeResponse.builder()
                .mode(roomModeRequest.getMode())
                .build();
    }

    public ParticipantResponse updateParticipants(ParticipantRequest participantRequest){
        eventObjectRepository.updateParticipant(participantRequest.getRoomId(), participantRequest.getParticipant());
        return ParticipantResponse.builder()
                .participantName(participantRequest.getParticipant())
                .build();
    }

    public PlaceResponse updatePlaces(PlaceRequest placeRequest){
        eventObjectRepository.updatePlaces(placeRequest.getRoomId(), placeRequest.getPlace());
        return PlaceResponse.builder()
                .place(placeRequest.getPlace())
                .build();
    }

    public MessageResponse updateChatMessages(MessageRequest message){
//        if (message.getStatus().equals(Status.JOIN)){
//            return;
//        }

        ChatMessage chatMessage = ChatMessage.builder()
                .message(message.getMessage())
                .senderName(message.getSenderName())
                .roomId(message.getRoomId())
                .time(LocalDateTime.now())
                .build();
        eventObjectRepository.updateChatMessages(message.getRoomId(), chatMessage);

        return MessageResponse.builder()
                .senderName(chatMessage.getSenderName())
                .message(chatMessage.getMessage())
                .time(chatMessage.getTime().toString())
                .build();
    }

    public List<SimpleEventObjectDTO> getAllEvents() {
        return eventObjectRepository.getAllEvents()
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

        if (byIdAndOwnerId.isEmpty()){
            throw new BadRequest("test");
        }
        byIdAndOwnerId.get().setChannelType(ChannelType.valueOf(request.getChannelType()));
        hangoverEventRepository.save(byIdAndOwnerId.get());

        return ResponseHelper.buildSuccessResponse("changed channel type");
    }
}
