package com.sumin.homeet.controller;


import com.sumin.homeet.domain.User;
import com.sumin.homeet.service.JwtService;
import com.sumin.homeet.service.KakaoService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth")
public class KakaoController {
    private final KakaoService kakaoService;
    private final JwtService jwtService;
    @ResponseBody
    @GetMapping("/kakao")
    public void kakaoClient(@RequestParam String code) {
        System.out.println("code = " + code);
        String accessToken = kakaoService.getKakaoAccessToken(code);
        kakaoService.getKakaoUser(accessToken);

        //jwt 생성
        jwtService.createToken(new User());
    }
}
