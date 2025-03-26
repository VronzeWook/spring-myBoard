package com.example.myBoard.domain.comment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    private String content;
    private Long postId;  // 게시물 ID (댓글이 달릴 게시물)
}
