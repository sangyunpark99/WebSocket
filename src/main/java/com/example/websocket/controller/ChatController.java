package com.example.websocket.controller;

import com.example.websocket.dto.ChatMessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate template;

    @MessageMapping(value = "/chat/enter")
    public void enterRoom(ChatMessageDto messageDto){
        messageDto.setMessage(messageDto.getWriter() + "님이 채팅방에 참여하였습니다.");
        template.convertAndSend("/sub/chat/room/" + messageDto.getRoomId(), messageDto);
    }

    @MessageMapping(value = "/chat/message")
    public void message(ChatMessageDto messageDto){
        template.convertAndSend("/sub/chat/room/" + messageDto.getRoomId(),messageDto);
    }
}
