package com.sumin.homeet.controller;


import com.sumin.homeet.jwt.JwtService;
import com.sumin.homeet.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class KakaoController {
    private final KakaoService kakaoService;
    @ResponseBody
    @GetMapping("/kakao")
    public ResponseEntity<Map<String,String>> kakaoClient(@RequestParam String code) {
        System.out.println("code = " + code);
        String accessToken = kakaoService.getKakaoAccessToken(code);
        String token = kakaoService.getKakaoUser(accessToken);
        Map<String,String> msg = new HashMap<>();
        msg.put("message", "success");
        return ResponseEntity.ok().header("X-AUTH-TOKEN",token).body(msg);

    }
}
