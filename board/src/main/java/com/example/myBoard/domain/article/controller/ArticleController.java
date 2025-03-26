package com.example.myBoard.domain.article.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class ArticleController {

    @GetMapping("home")
    public String home() {
        return "example";
    }

}
