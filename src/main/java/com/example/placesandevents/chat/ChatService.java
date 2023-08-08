package com.example.placesandevents.chat;

import com.example.placesandevents.domain.chat.ChatMessage;
import com.example.placesandevents.domain.chat.repository.ChatRepository;
import com.example.placesandevents.websockettest.model.in.MessageRequest;
import com.example.placesandevents.websockettest.model.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    public void processMessage(MessageRequest message){
        if (message.getStatus().equals(Status.JOIN)){
            return;
        }
        ChatMessage chatMessage = ChatMessage.builder()
                .message(message.getMessage())
                .senderName(message.getSenderName())
                .roomId(message.getRoomId())
                .build();

        chatRepository.save(chatMessage);
    }

    public List<ChatMessage> getAllChatMessagesByRoom(String roomId){
        return chatRepository.findAllByChatId(roomId);
    }
}
