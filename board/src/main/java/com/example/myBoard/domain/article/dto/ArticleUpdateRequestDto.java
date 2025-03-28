package com.example.myBoard.domain.article.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ArticleUpdateRequestDto {
    private String title;
    private String content;
    private String password;
}