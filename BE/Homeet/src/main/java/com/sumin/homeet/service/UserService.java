package com.sumin.homeet.service;


import com.sumin.homeet.domain.User;
import com.sumin.homeet.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public Long register(User user){
        validationDupUser(user);
        userRepository.save(user);
        return user.getId();
    }

    public String validationDupUser(User user) {
        List<User> users = userRepository.findByEmail(user.getEmail());
        String msg="";
        //예외처리 수정 필요
        try{
            if (!users.isEmpty()){
                msg = "User exists";
            }
            else{
                register(user);
                msg = "register complete";
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return msg;
    }
    public User findOne(Long userId){return userRepository.findById(userId).get();}

}
