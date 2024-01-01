package com.example.websocket.controller;

import com.example.websocket.dto.ChatRoomDto;
import com.example.websocket.repository.ChatRoomRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
@Slf4j
public class RoomController {

    private final ChatRoomRepository chatRoomRepository;

    @GetMapping(value = "/rooms")
    public List<ChatRoomDto> rooms(){
        log.info("채팅방 조회하기");
        return chatRoomRepository.findAllRoom();
    }

    @PostMapping(value = "/room")
    public String create(@RequestBody String name){
        log.info("채팅방 생성 : 이름 {}", name);
        return chatRoomRepository.createChatRoom(name).getRoomId();
    }

    @GetMapping("/room")
    public String getRoom(@RequestBody String roomId){
        log.info("채팅방 조회 : {}", roomId);
        return chatRoomRepository.findRoomById(roomId).getRoomId();
    }
}