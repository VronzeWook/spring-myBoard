package com.example.myBoard.domain.article.service;

import com.example.myBoard.domain.article.dto.ArticleDeleteRequestDto;
import com.example.myBoard.domain.article.dto.ArticleCreateRequestDto;
import com.example.myBoard.domain.article.dto.ArticleResponseDto;
import com.example.myBoard.domain.article.dto.ArticleUpdateRequestDto;

import java.util.List;

public interface ArticleService {
    // 요청 dto 기반 게시물 생성
    Long createArticle(ArticleCreateRequestDto dto);
    // ID로 게시물 조회
//    ArticleResponseDto getArticle(Long id, HttpServletRequest request, HttpServletResponse response);
    ArticleResponseDto getArticleDetail(Long id); // http 의존성 제거
    // 게시물 목록 조회
    List<ArticleResponseDto> getArticleList();
    // 요청 dto 기반 게시물 수정
    void updateArticle(Long id, ArticleUpdateRequestDto dto);
    // 요청 dto 기반 게시물 삭제
    void deleteArticle(Long id, ArticleDeleteRequestDto dto);
    // 게시물 조회수 증가
    void increaseViewCount(Long id);
}