package com.example.placesandevents.domain.hangoverevent.repository;

import com.example.placesandevents.domain.chat.ChatMessage;
import com.example.placesandevents.domain.hangoverevent.ChannelType;
import com.example.placesandevents.domain.hangoverevent.EventObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class EventObjectRepository {

    private final Map<String, EventObject> eventDb = new HashMap<>();

    public void save(EventObject eventObject) {
        eventDb.put(eventObject.getEventName(), eventObject);
        System.out.println("EVENTDB: " + eventDb);
    }

    public void addParticipant(String eventId, String participant){
        EventObject eventObject = eventDb.get(eventId);
        eventObject.getParticipants().add(participant);
        eventDb.put(eventId, eventObject);
    }

    public void removeParticipant(String eventId, String participant){
        EventObject eventObject = eventDb.get(eventId);
        eventObject.getParticipants().remove(participant);
        eventDb.put(eventId, eventObject);
    }

    public void addPlace(String eventId, String place){
        EventObject eventObject = eventDb.get(eventId);
        eventObject.getPlaces().add(place);
        eventDb.put(eventId, eventObject);
    }

    public void removePlace(String eventId, String place){
        EventObject eventObject = eventDb.get(eventId);
        eventObject.getPlaces().remove(place);
        eventDb.put(eventId, eventObject);
    }
    public void updateChatMessages(String eventId, ChatMessage message){
        EventObject eventObject = eventDb.get(eventId);
        eventObject.getChatMessages().add(message);
        eventDb.put(eventId, eventObject);
    }

    public void updateRoomType(String eventId, String mode){
        EventObject eventObject = eventDb.get(eventId);
        eventObject.setChannelType(ChannelType.valueOf(mode));
        eventDb.put(eventId, eventObject);
    }

    public List<EventObject> getAllEvents(String user) {
        return new ArrayList<>(eventDb.values())
                .stream()
                .filter(event -> event.getParticipants().contains(user))
                .collect(Collectors.toList());
    }

    public EventObject getEventObjectById(String eventId){
        return eventDb.getOrDefault(eventId, new EventObject());
    }
}
