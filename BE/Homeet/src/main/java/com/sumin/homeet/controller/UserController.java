package com.sumin.homeet.controller;

import com.google.gson.JsonObject;
import com.sumin.homeet.domain.User;
import com.sumin.homeet.jwt.JwtService;
import com.sumin.homeet.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    //access token을 받아오는 과정?
    private final JwtService jwtService;
    @PostMapping("/")
    public String test(@RequestHeader HttpHeaders headers){
        System.out.println("headers.toString() = " + headers.toString());
        System.out.println(" = ");
        return "test";
    }



}
