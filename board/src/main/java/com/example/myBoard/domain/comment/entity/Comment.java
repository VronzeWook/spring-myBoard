package com.example.myBoard.domain.comment.entity;

import com.example.myBoard.domain.article.entity.Article;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;

    @Column(nullable = false, length = 300)
    private String content;

    @Column(nullable = false, length = 30)
    private String author;

    @Column(nullable = false)
    private String password;

    @Builder
    public Comment(Article article, String content, String author, String password) {
        this.article = article;
        this.content = content;
        this.author = author;
        this.password = password;
    }

    // 댓글 수정 메서드
    public void update(String content) {
        this.content = content;
    }
}