package com.sumin.homeet.dto;

import com.sumin.homeet.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoteSendRecvDto {
    private Long id;
    private String content;
    private Long receiverId;
    private LocalDateTime createDate;
    private Long senderId;

}
