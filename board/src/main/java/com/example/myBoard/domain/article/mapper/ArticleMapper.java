package com.example.myBoard.domain.article.mapper;

import com.example.myBoard.domain.article.dto.ArticleCreateRequestDto;
import com.example.myBoard.domain.article.dto.ArticleResponseDto;
import com.example.myBoard.domain.article.entity.Article;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Article toEntity(ArticleCreateRequestDto dto) {
        return Article.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .author(dto.getAuthor())
                .password(encoder.encode(dto.getPassword()))
                .build();
    }

    public ArticleResponseDto toDto(Article article) {
        return new ArticleResponseDto(article);
    }
}
