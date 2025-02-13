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

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public boolean loginUser(String userId, String password) {

        Optional<User> t = userRepository.findByUserId(userId);
        if (t != null) {
            // 비밀번호가 일치하는지 확인
            if (passwordEncoder.matches(password, user.getPassword())) {
                return true; // 인증 성공
            } else {
                return false; // 비밀번호 불일치
            }
        } else {
            return false; // 사용자 없음
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
