package com.sumin.homeet.controller;

import com.google.gson.JsonObject;
import com.sumin.homeet.domain.User;
import com.sumin.homeet.jwt.JwtService;
import com.sumin.homeet.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    //access token을 받아오는 과정?
    private final JwtService jwtService;
    @GetMapping("/")
    public String validate(@RequestHeader("X-AUTH-TOKEN") String token){
        return "test";
    }
//    @ResponseBody
//    @PostMapping("/register")
//    public String registerUser(@RequestBody @Valid UserReqRegister req){
//        JsonObject res = new JsonObject();
//        try {
//            userService.register(makeUser(req));
//        } catch (Exception e){
//
//        }finally {
//            res.addProperty("status",200);
//            return res.toString();
//        }
//    }
//    @Data
//    static class UserReqRegister{
//
//        private String email;
//        private String pwd;
//        private String nickName;
//        private String phone;
//    }
//    private User makeUser(UserReqRegister req){
//        User user = new User();
//        user.setEmail(req.getEmail());
//        user.setNickname(req.getNickName());
//        return user;
//    }


}
