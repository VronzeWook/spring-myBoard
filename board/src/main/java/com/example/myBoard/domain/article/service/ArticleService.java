package com.example.myBoard.domain.article.service;

import com.example.myBoard.domain.article.dto.ArticleRequestDto;
import com.example.myBoard.domain.article.dto.ArticleResponseDto;
import com.example.myBoard.domain.article.entity.Article;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface ArticleService {
    // 요청 dto 기반 게시물 생성
    Long createArticle(ArticleRequestDto dto);
    // ID로 게시물 조회
    ArticleResponseDto getArticle(Long id, HttpServletRequest request, HttpServletResponse response);
    // 게시물 목록 조회
    List<ArticleResponseDto> getArticleList();
    // 요청 dto 기반 게시물 수정
    void updateArticle(Long id, ArticleRequestDto dto);
    // 요청 dto 기반 게시물 삭제
    void deleteArticle(Long id, String inputPassword);
}