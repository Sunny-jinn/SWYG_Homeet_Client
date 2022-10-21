package com.sumin.homeet.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    String email;
    String nickname;
    Long userId;
}
