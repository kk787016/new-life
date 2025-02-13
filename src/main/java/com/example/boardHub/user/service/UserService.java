package com.example.boardHub.user.service;


import com.example.boardHub.user.model.User;
import com.example.boardHub.user.repository.SpringUserRepository;
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


    public User registerUser(User user) {
        return userRepository.save(user);
    }


}
