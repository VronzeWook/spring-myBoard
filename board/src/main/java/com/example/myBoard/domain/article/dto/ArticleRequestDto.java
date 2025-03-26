package com.example.myBoard.domain.article.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class ArticleRequestDto {
    private String title;
    private String content;
    private String author;
    private String password; // 평문으로 받아와서 해싱처리
}