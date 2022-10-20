package com.sumin.homeet.service;


import com.sumin.homeet.domain.User;
import com.sumin.homeet.jwt.JwtService;
import com.sumin.homeet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    @Transactional
    public Long register(User user){
        User userSave = userRepository.save(user);
        return userSave.getId();
    }

    @Transactional
    public List<String> validationDupUser(User user) {
        User users = userRepository.findByEmail(user.getEmail());
        List<String> lists = new ArrayList<>();
        String msg="";
        Long userId=0L;
        //예외처리 수정 필요
        try{
            if (users != null){
                msg = "User exists";
                userId = users.getId();
            }
            else{
                userId = register(user);
                msg = "register complete";
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        String token = jwtService.createToken(user);
        System.out.println("userService token = " + token);
        lists.add(token);
        lists.add(userId.toString());
        return lists;
    }
    public User findOne(Long userId){return userRepository.findById(userId).get();}
    public User findOneByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public String getNickname(String email){
        User user = userRepository.findByEmail(email);
        return user.getNickname();
    }
}
