package com.example.boardHub.user.controller;

import com.example.boardHub.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
//@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerForm() {
        return "user/register";
    }
    @GetMapping("/login")
    public String loginForm() {
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userId, @RequestParam String password, RedirectAttributes redirectAttributes) {

        boolean isLoginSuccessful  = userService.loginUser(userId, password);
        if (!isLoginSuccessful){
            redirectAttributes.addFlashAttribute("아이디 또는 비밀번호가 잘못되었습니다.");
            return "redirect:/login";
        }
        return "redirect:/";

    }

    @PostMapping("/register")
    public String register(@RequestParam String userId, @RequestParam String password, @RequestParam String nickname, Model model, RedirectAttributes redirectAttributes) {

        try {
            userService.registerUser(userId, password, nickname);
            //model.addAttribute("회원 가입에 성공했습니다.");    //TODO 질문.
            return "redirect:/";  // 회원가입 후 로그인 페이지로 리다이렉트

        } catch (IllegalStateException e) {
            //model.addAttribute("errorMessage", e.getMessage());  // 중복 에러 메시지 전달
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/register";  // 회원가입 페이지로 다시 이동
        }

    }
}
