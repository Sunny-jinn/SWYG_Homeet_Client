package com.sumin.homeet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReqNoteDto {
    private String content;
    private Long sendUserId; //쪽지 보낸 사람id
    private Long receiverId; //받는 사람id
    private Long roomId; //방 id
}
