package com.example.myBoard.domain.comment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class CommentRequestDto {
    private String content;
    private String password;
    private String author;
}
