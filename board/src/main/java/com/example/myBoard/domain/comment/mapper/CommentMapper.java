package com.example.myBoard.domain.comment.mapper;

import com.example.myBoard.domain.comment.dto.CommentRequestDto;
import com.example.myBoard.domain.comment.dto.CommentResponseDto;
import com.example.myBoard.domain.comment.entity.Comment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Comment toEntity(CommentRequestDto dto) {
        return Comment.builder()
                .author(dto.getAuthor())
                .content(dto.getContent())
                .password(encoder.encode(dto.getPassword()))
                .build();
    }

    public CommentResponseDto toDto(Comment comment) {
        return new CommentResponseDto(comment);
    }
}
