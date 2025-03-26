package com.example.myBoard.domain.comment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDto {
    private Long id;
    private String content;
    private String postTitle;  // 댓글이 달린 게시물 제목
}