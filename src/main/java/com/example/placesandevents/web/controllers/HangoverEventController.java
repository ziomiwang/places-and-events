package com.example.placesandevents.web.controllers;

import com.example.placesandevents.commons.dto.OperationResponseDTO;
import com.example.placesandevents.domain.hangoverevent.EventObject;
import com.example.placesandevents.hangoverevent.HangoverEventService;
import com.example.placesandevents.hangoverevent.dto.CreateEventObjectDTO;
import com.example.placesandevents.hangoverevent.dto.SimpleEventObjectDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
public class HangoverEventController {

    private final HangoverEventService eventService;
    private final ChatService chatService;

    @PostMapping("/new")
    public ResponseEntity<OperationResponseDTO> createNewEventObject(@RequestBody CreateEventObjectDTO eventObject, @RequestParam String username) {
        return ResponseEntity.ok(eventService.createNewEventObject(eventObject, username));
    }

    @GetMapping("/all")
    public ResponseEntity<List<SimpleEventObjectDTO>> getAllEventObjects(@RequestParam String username) {
        return ResponseEntity.ok(eventService.getAllEvents(username));
    }

    @GetMapping("/single/{eventId}")
    public ResponseEntity<EventObject> getSingleEventObjectById(@PathVariable String eventId){
        return ResponseEntity.ok(eventService.getSingleEventObjectById(eventId));
    }
//
//    @PostMapping("/new")
//    public ResponseEntity<OperationResponseDTO> createNewHangoverEvent(@RequestBody HangoverEventRequestDTO request, Principal principal) {
//        return ResponseEntity.ok(eventService.createNewHangoverEvent(request, principal));
//    }

//    @GetMapping("/chat/{chatId}")
//    public ResponseEntity<List<ChatMessage>> getAllMessagesByChatId(@PathVariable String chatId){
//       return ResponseEntity.ok(chatService.getAllChatMessagesByRoom(chatId));
//    }
}
