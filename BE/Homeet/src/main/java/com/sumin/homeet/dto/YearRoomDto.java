package com.sumin.homeet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class YearRoomDto {
    private Long id;
    private boolean duplex;
    private String location;
    private int yearPrice;
    private String content;
}
