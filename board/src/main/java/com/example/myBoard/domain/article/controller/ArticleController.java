package com.example.myBoard.domain.article.controller;

import com.example.myBoard.domain.article.dto.ArticleDeleteRequestDto;
import com.example.myBoard.domain.article.dto.ArticleCreateRequestDto;
import com.example.myBoard.domain.article.dto.ArticleResponseDto;
import com.example.myBoard.domain.article.dto.ArticleUpdateRequestDto;
import com.example.myBoard.domain.article.service.ArticleService;
import com.example.myBoard.global.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.management.HeapDumpWebEndpoint;
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
        List<ArticleResponseDto> articles = articleService.getArticleList();
        model.addAttribute("articles", articles);
        return "articles/list";
    }

    // 2. 글쓰기 폼
    @GetMapping("/create")
    public String createArticleForm(Model model) {
        model.addAttribute("article", new ArticleCreateRequestDto());
        return "articles/create";
    }

    // 3. 글 작성 처리
    @PostMapping("/create")
    public String createArticle(@ModelAttribute ArticleCreateRequestDto dto) {
        Long id = articleService.createArticle(dto);
        return "redirect:/articles/" + id;
    }

    // 4. 게시글 상세
    @GetMapping("/{id:[0-9]+}")
    public String articleDetail(@PathVariable Long id, Model model, HttpServletRequest request, HttpServletResponse response) {

        // 쿠키 유효성 검사
        String cookiesName = "viewed_" + id;
        boolean isFirstVisit = !CookieUtil.hasCookie(request, cookiesName);
        if (isFirstVisit) {
            articleService.increaseViewCount(id); // 조회수 증가
            CookieUtil.addCookie(response, cookiesName, "true", 60 * 30);
        }

        // 게시글 조회
        ArticleResponseDto article = articleService.getArticleDetail(id);
        model.addAttribute("article", article);
        return "articles/detail";
    }

    // 5. 수정 폼
    @GetMapping("/{id}/update")
    public String updateArticleForm(@PathVariable Long id, Model model) {
        ArticleResponseDto article = articleService.getArticleDetail(id);
        model.addAttribute("article", article);
        return "articles/update";
    }

    // 6. 수정 처리
    @PostMapping("/{id}/update")
    public String updateArticle(@PathVariable Long id, @ModelAttribute ArticleUpdateRequestDto dto) {
        System.out.println("id = " + id);
        System.out.println("dto title = " + dto.getTitle());
        System.out.println("dto password = " + dto.getPassword());
        articleService.updateArticle(id, dto);
        return "redirect:/articles/" + id;
    }

    // 7. 삭제 처리
    @PostMapping("/{id}/delete")
    public String deleteArticle(@PathVariable Long id, @ModelAttribute ArticleDeleteRequestDto dto) {
        articleService.deleteArticle(id, dto);
        return "redirect:/articles";
    }
}
