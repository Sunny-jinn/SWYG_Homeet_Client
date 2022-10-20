package com.sumin.homeet.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegRoomDto {
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
