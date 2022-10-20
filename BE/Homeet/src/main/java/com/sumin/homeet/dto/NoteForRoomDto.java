package com.sumin.homeet.dto;

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
public class NoteForRoomDto {
    private Long id;
    private String location;
    private String content;
    private Boolean duplex;
}
