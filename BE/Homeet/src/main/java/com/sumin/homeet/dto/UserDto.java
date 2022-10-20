package com.sumin.homeet.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    String email;
    String nickname;
    Long userId;
}
