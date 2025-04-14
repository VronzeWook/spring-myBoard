package com.example.myBoard.domain.article.dto;

import com.example.myBoard.domain.article.entity.Article;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class ArticleResponseDto {
    private final Long id;
    private final String title;
    private final String author;
    private final String content;
    private final int viewCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ArticleResponseDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.author = article.getAuthor();
        this.content = article.getContent();
        this.viewCount = article.getViewCount();
        this.createdAt = article.getCreateAt();
        this.updatedAt = article.getUpdateAt();
    }
}
