package com.example.boardHub.user.controller;

import com.example.boardHub.user.dto.UserRequestDto;
import com.example.boardHub.user.model.User;
import com.example.boardHub.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/user")


public class UserController{

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/register")
    public String showRegisterPage() {
        return "user/register"; // templates/user/register.html을 렌더링
    }
    @GetMapping("/login")
    public String showLoginPage() {
        return "user/login"; // templates/user/register.html을 렌더링
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRequestDto userDto) {
        try {
            userService.registerUser(userDto.getUserId(), userDto.getPassword(), userDto.getNickname());
            return ResponseEntity.ok().body(Map.of("message", "회원가입 성공"));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserRequestDto userDto, HttpSession session) {
        boolean isLoginSuccessful = userService.loginUser(userDto.getUserId(), userDto.getPassword());
        if (!isLoginSuccessful) {
            return ResponseEntity.status(401).body(Map.of("error", "아이디 또는 비밀번호가 잘못되었습니다."));
        }

        session.setAttribute("loginUser", userDto.getUserId()); // 로그인 세션 저장
        return ResponseEntity.ok().body(Map.of("message", "로그인 성공"));
    }

}
