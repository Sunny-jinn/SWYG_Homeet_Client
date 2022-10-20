package com.sumin.homeet.dto;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class RoomDto {
    private Long id;
    private String dtype;
    private String location;
    private String content;
    private List<String> imageUrl = new ArrayList<>();
    private Boolean duplex;
    private int onePrice;
    private int perPrice;
    private int yearPrice;
    private UserDto user;
}
