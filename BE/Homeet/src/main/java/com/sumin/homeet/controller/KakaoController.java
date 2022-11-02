package com.sumin.homeet.controller;


import com.sumin.homeet.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class KakaoController {
    private final KakaoService kakaoService;
    @GetMapping("/kakao")
    public ResponseEntity<Map<String,String>> kakaoClient(@RequestParam String code) {
        System.out.println("code = " + code);
        HttpHeaders httpHeaders = new HttpHeaders();
        String accessToken = kakaoService.getKakaoAccessToken(code);
        List<String> token = kakaoService.getKakaoUser(accessToken);

        Map<String,String> msg = new HashMap<>();
        httpHeaders.add("X-AUTH-TOKEN", token.get(0));
        msg.put("token", token.get(0));
        msg.put("userId", token.get(1));
        msg.put("nickname", token.get(2));
        return ResponseEntity.ok().headers(httpHeaders).body(msg);

    }
}
