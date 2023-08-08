package com.example.placesandevents.domain.chat.repository;

import com.example.placesandevents.domain.chat.ChatMessage;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ChatRepository {

    private final Map<String, List<ChatMessage>> chatDb = new HashMap<>();

    public void save(ChatMessage chatMessage) {
        chatDb.computeIfAbsent(chatMessage.getRoomId(), d -> new ArrayList<>()).add(chatMessage);
        System.out.println("Db STATE: " + chatDb);
    }

    public List<ChatMessage> findAllByChatId(String chatId) {
        return chatDb.getOrDefault(chatId, Collections.emptyList());
    }
}
