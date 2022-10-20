package com.sumin.homeet.controller;

import com.sumin.homeet.dto.ReqNoteDto;
import com.sumin.homeet.dto.RoomNoteDto;
import com.sumin.homeet.jwt.JwtService;
import com.sumin.homeet.service.NoteService;
import com.sumin.homeet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;
    private final JwtService jwtService;

    @PostMapping("/send")
    public ResponseEntity<Map<String,String>> postSend(@RequestBody ReqNoteDto note){
        Map<String,String> data = new HashMap<>();
        noteService.registerNote(note);
        data.put("message", "send success");
        return ResponseEntity.ok().body(data);

    }
    @GetMapping("/all")
    public ResponseEntity<Map<String,List<RoomNoteDto>>> getAll(){
        String email = jwtService.getEmail();
        Map<String,List<RoomNoteDto>> data = new HashMap<>();
        List<RoomNoteDto> roomNote = noteService.findRoomNote(email);
        data.put("data",roomNote);
        return ResponseEntity.ok().body(data);

    }
    @GetMapping("/")
    public ResponseEntity<Map<String,RoomNoteDto>> getRoomNote(@RequestParam("roomNote_id") Long roomNoteId){
        RoomNoteDto roomNoteOne = noteService.findRoomNoteOne(roomNoteId);
        Map<String,RoomNoteDto> data = new HashMap<>();
        data.put("data", roomNoteOne);
        return ResponseEntity.ok().body(data);

    }

}
