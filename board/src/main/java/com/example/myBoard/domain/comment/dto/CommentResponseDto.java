package com.example.myBoard.domain.comment.dto;

import com.example.myBoard.domain.article.entity.Article;
import com.example.myBoard.domain.comment.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private final Long id;
    private final String content;
    private final String author;
    private final LocalDateTime updatedAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.author = comment.getAuthor();
        this.updatedAt = comment.getUpdateAt();
    }
}