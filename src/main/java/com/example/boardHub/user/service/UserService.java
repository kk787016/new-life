package com.example.boardHub.user.service;


import com.example.boardHub.user.model.User;
import com.example.boardHub.user.repository.SpringUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private SpringUserRepository userRepository;

    public UserService(SpringUserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public boolean loginUser(String userId, String password) {

        boolean check_useID = userRepository.findByUserId(userId).isPresent();
        if (check_useID){
            if(check_useID != true && passwordEncoder.ma)
            return true;
        } else {
            throw new IllegalStateException("존재하지 않는 아이디입니다.");
        }
    }


    @Transactional
    public void registerUser(String userId, String password, String nickname) {
        if (userRepository.findByUserId(userId).isPresent()) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
        if (userRepository.findByNickname(nickname).isPresent()) {
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        }

        // 새로운 사용자 저장
        User user = new User(userId, password, nickname);  // createdDate 자동 설정됨
        userRepository.save(user);
    }


}
