package com.example.boardHub.board.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    // @Autowired
    // private BoardService boardService;

    @GetMapping("/boards")
    public String list() {
        return "board/list";
    }

    @GetMapping("boards/new")
    public String createForm() {
        return "board/create";
    }

    @GetMapping("boards/{id}")
    public String detail() {
        return "board/detail";
    }
    @GetMapping("/register")
    public String registerForm() {
        return "user/register";
    }
    @GetMapping("/login")
    public String loginForm() {
        return "user/login";
    }
}