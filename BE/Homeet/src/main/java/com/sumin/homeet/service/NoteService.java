package com.sumin.homeet.service;

import com.sumin.homeet.domain.Note;
import com.sumin.homeet.domain.RoomNote;
import com.sumin.homeet.domain.User;
import com.sumin.homeet.domain.room.Room;
import com.sumin.homeet.dto.*;
import com.sumin.homeet.jwt.JwtService;
import com.sumin.homeet.repository.NoteRepository;
import com.sumin.homeet.repository.RoomNoteRepository;
import com.sumin.homeet.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final RoomNoteRepository roomNoteRepository;

    private final RoomService roomService;
    private final UserService userService;
    public RoomNoteDto findRoomNoteOne(Long roomNoteId){
        RoomNote roomNote = roomNoteRepository.findById(roomNoteId).get();
        return getRoomNoteDto(roomNote);
    }

    private static RoomNoteDto getRoomNoteDto(RoomNote roomNote) {
        Room room = roomNote.getRoom();
        List<NoteSendRecvDto> newNoteList = new ArrayList<>();
        for (Note note : roomNote.getNoteList()) {
            newNoteList.add(NoteSendRecvDto.builder()
                    .id(note.getId())
                    .senderId(note.getSendUser().getId())
                    .receiverId(note.getReceiverId())
                    .createDate(note.getCreateDate())
                    .content(note.getContent())
                    .build()
            );
        }
        NoteForRoomDto noteRoomDto = NoteForRoomDto.builder()
                .id(room.getId())
                .duplex(room.getDuplex())
                .location(room.getLocation())
                .content(room.getContent())
                .build();
        return RoomNoteDto.builder()
                .noteList(newNoteList)
                .noteForRoomDto(noteRoomDto)
                .build();
    }

    public List<RoomNoteDto> findRoomNote(String email){
        User user = userService.findOneByEmail(email);
        List<RoomNote> newList = new ArrayList<>();
        //list filter for userId
        for (RoomNote roomNote1 : roomNoteRepository.findAll()) {
            List<Note> noteList = roomNote1.getNoteList();
            Boolean check = false;
            for (Note note : noteList) {
                if (user.getId().equals(note.getReceiverId()) ||
                        user.getId().equals(note.getSendUser().getId())) {
                    check = true;
                    break;
                }
            }
            if (check) {
                newList.add(roomNote1);
            }
        }
        // dto trans
        List<RoomNoteDto> result = new ArrayList<>();
        newList.forEach(roomNote -> {
            result.add(getRoomNoteDto(roomNote));
        });
        return result;
    }

    public void registerNote(ReqNoteDto note){
        Long roomNoteId = 0L;
        List<Long> result = new ArrayList<>();
        for (RoomNote roomNote : roomNoteRepository.findAll()) {
            Boolean check0 = false;
            if (roomNote.getRoom().getId().equals(note.getRoomId())) {
                for (Note tempNote : roomNote.getNoteList()) {
                    if ((tempNote.getReceiverId().equals(note.getReceiverId()) ||
                            tempNote.getReceiverId().equals(note.getSendUserId())) &&
                            (tempNote.getSendUser().getId().equals(note.getReceiverId()) ||
                                    tempNote.getSendUser().getId().equals(note.getSendUserId()))) {
                        check0 = true;
                        break;
                    }
                }
            }
            if (check0) {
                roomNoteId=roomNote.getId();
                break;
            }
        }
        //기존 룸노트에 추가
        if (!roomNoteId.equals(0L)){
            Note sNote = new Note();
            sNote.setSendUser(userService.findOne(note.getSendUserId()));
            sNote.setContent(note.getContent());
            sNote.setReceiverId(note.getReceiverId());
            sNote.setCreateDate(LocalDateTime.now());
            RoomNote roomNote = roomNoteRepository.findById(roomNoteId).get();
            sNote.setRoomNote(roomNote);
            noteRepository.save(sNote);
            roomNote.addList(sNote);
        }
        else{
            //새로운 룸노트 만들기
            Note sNote = new Note();
            sNote.setSendUser(userService.findOne(note.getSendUserId()));
            sNote.setContent(note.getContent());
            sNote.setReceiverId(note.getReceiverId());
            sNote.setCreateDate(LocalDateTime.now());

            RoomNote roomNote = new RoomNote();
            Room room = roomService.findRoomOne(note.getRoomId());
            roomNote.setRoom(room);
            Long roomUserID = room.getUserRoom().getId();
            roomNote.setUserRoomNote(userService.findOne(roomUserID));
            RoomNote saveRoom = roomNoteRepository.save(roomNote);

            sNote.setRoomNote(saveRoom);
            noteRepository.save(sNote);
        }
    }
}
