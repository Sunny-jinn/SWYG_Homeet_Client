package com.sumin.homeet.dto;


import com.sumin.homeet.domain.Note;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomNoteDto {
    private List<NoteSendRecvDto> noteList = new ArrayList<>();
    private NoteForRoomDto noteForRoomDto;
}
