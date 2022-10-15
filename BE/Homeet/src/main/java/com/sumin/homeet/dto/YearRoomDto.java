package com.sumin.homeet.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class YearRoomDto {
    private Long id;
    private boolean duplex;
    private String location;
    private int yearPrice;
    private String content;
}
