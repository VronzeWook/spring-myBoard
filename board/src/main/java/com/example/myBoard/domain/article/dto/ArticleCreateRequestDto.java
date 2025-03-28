package com.example.myBoard.domain.article.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ArticleCreateRequestDto {
    private String title;
    private String content;
    private String author;
    private String password;
}