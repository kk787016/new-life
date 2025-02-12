package com.example.boardHub.user.controller;

import com.example.boardHub.user.model.User;
import com.example.boardHub.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
//@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerForm() {
        return "user/register";
    }
    @GetMapping("/login")
    public String loginForm() {
        return "user/login";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        userService.registerUser(user);
        return "redirect:/user/login";  // 회원가입 후 로그인 페이지로 리다이렉트
    }
}
