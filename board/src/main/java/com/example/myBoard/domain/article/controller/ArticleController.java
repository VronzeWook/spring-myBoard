package com.example.myBoard.domain.article.controller;

import com.example.myBoard.domain.article.dto.ArticleRequestDto;
import com.example.myBoard.domain.article.dto.ArticleResponseDto;
import com.example.myBoard.domain.article.service.ArticleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    // 1. 게시글 목록
    @GetMapping
    public String getArticleList(Model model) {
        // Service 계층에서 게시글 리스트를 불러옴
        List<ArticleResponseDto> articles = articleService.getArticleList();
        model.addAttribute("articles", articles);
        return "articles/list";
    }

    // 2. 글쓰기 폼
    @GetMapping("/new")
    public String newArticle(Model model) {
        model.addAttribute("article", new ArticleRequestDto());
        return "articles/new";
    }

    // 3. 글 작성 처리
    @PostMapping
    public String createArticle(@ModelAttribute ArticleRequestDto dto) {
        Long id = articleService.createArticle(dto);
        return "redirect:/articles/" + id;
    }

    // 4. 게시글 상세
    @GetMapping("/{id}")
    public String articleDetail(@PathVariable Long id, Model model, HttpServletRequest request, HttpServletResponse response) {
        ArticleResponseDto article = articleService.getArticle(id, request, response);
        model.addAttribute("article", article);
        return "articles/detail";
    }

    // 5. 수정 폼
    @GetMapping("/{id}/edit")
    public String editArticleForm(@PathVariable Long id, @ModelAttribute ArticleRequestDto dto) {

        return "articles/edit";
    }

    // 6. 수정 처리

    // 7. 삭제 처리


}
