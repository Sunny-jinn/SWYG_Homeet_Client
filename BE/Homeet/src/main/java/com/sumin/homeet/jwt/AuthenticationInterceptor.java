package com.sumin.homeet.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sumin.homeet.domain.User;
import com.sumin.homeet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {
    private final JwtService jwtService;
//    private final ObjectMapper objectMapper;
    private final UserService userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        try {
            String email = jwtService.getEmail();
            User user = userService.findOneByEmail(email);
            if (user.getEmail()==email){
                System.out.println(" =success ");
                return true;
            }

        } catch (Exception e){
            return false;
        }
        return true;
    }

}
