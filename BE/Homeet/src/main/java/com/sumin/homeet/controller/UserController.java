package com.sumin.homeet.controller;

import com.google.gson.JsonObject;
import com.sumin.homeet.domain.User;
import com.sumin.homeet.jwt.JwtService;
import com.sumin.homeet.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    //access token을 받아오는 과정?
    private final JwtService jwtService;

    @GetMapping("/profile")
    public ResponseEntity<Map<String,String>> getProfile(){
        String email = jwtService.getEmail();
        Map<String,String> data = new HashMap<>();
        data.put("nickname",userService.getNickname(email));
        return ResponseEntity.ok().body(data);
    }



}
