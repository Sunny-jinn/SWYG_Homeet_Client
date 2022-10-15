package com.sumin.homeet.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MonthRoomDto {
    private Long id;
    private boolean duplex;
    private String location;
    private int OnePrice;
    private int PerPrice;
    private String content;
}
