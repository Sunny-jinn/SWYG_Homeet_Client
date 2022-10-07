package com.sumin.homeet.service;


import com.sumin.homeet.domain.User;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class JwtService {
    public String createToken(User user){
        return Jwts.builder()
                .setHeaderParam(Header.TYPE,Header.JWT_TYPE)
                .setIssuer("sumin")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + Duration.ofMinutes(30).toMillis()))
                .claim("email",user.getEmail())
                .claim("id",user.getId())
                .signWith(SignatureAlgorithm.HS256,"secret")
                .compact();
    }

}
