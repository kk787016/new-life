package com.example.boardHub.user.service;

import com.example.boardHub.user.model.User;
import com.example.boardHub.user.repository.SpringUserRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private PasswordEncoder passwordEncoder;

    public boolean loginUser(String userId, String password) {

        Optional<User> optionalUser = userRepository.findByUserId(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
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

        String encodedPassword = passwordEncoder.encode(password);
        // 새로운 사용자 저장
        User user = new User(userId, encodedPassword, nickname);  // createdDate 자동 설정됨
        userRepository.save(user);
    }


}
