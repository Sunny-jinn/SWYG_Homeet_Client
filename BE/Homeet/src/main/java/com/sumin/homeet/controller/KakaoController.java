package com.sumin.homeet.controller;


import com.sumin.homeet.jwt.JwtService;
import com.sumin.homeet.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class KakaoController {
    private final KakaoService kakaoService;
    @ResponseBody
    @GetMapping("/kakao")
    public String kakaoClient(@RequestParam String code) {
        System.out.println("code = " + code);
        String accessToken = kakaoService.getKakaoAccessToken(code);
        kakaoService.getKakaoUser(accessToken);

        return accessToken;
    }
}
