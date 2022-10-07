package com.sumin.homeet.jwt;


import com.sumin.homeet.domain.User;
import com.sumin.homeet.repository.UserRepository;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.util.*;

@Service
@RequiredArgsConstructor
public class JwtService {
    @Value("${SECRETKEY}")
    private String secretKey;
    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }
    private final UserRepository userRepository;
    public String createToken(User user){
        return Jwts.builder()
                .setHeaderParam(Header.TYPE,Header.JWT_TYPE)
                .setIssuer("sumin")
                .setIssuedAt(new Date())
                .setSubject(user.getId().toString())
                .claim("email",user.getEmail())
                .setExpiration(new Date(new Date().getTime() + Duration.ofMinutes(30).toMillis()))
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
    }

    public String getJwt() {
        HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        return req.getHeader("X-AUTH-TOKEN");
    }
    public String getEmail(){
        String token = getJwt();
        System.out.println("getEmail void token = " + token);
        Jws<Claims> claim = Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token);
        System.out.println("claim = " + claim);
        String email = claim.getBody().get("email", String.class);
        System.out.println("email = " + email);
        return email;
    }


}
