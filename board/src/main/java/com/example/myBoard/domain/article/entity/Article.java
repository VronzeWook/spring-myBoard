package com.example.myBoard.domain.article.entity;
import com.example.myBoard.domain.comment.entity.Comment;
import com.example.myBoard.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, length = 30)
    private String author;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private int viewCount = 0;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Article(String title, String content, String password, String author) {
        this.title = title;
        this.content = content;
        this.password = password;
        this.author = author;
    }

    // 조회 수 증가 메서드
    public void increaseViewCount() {
        this.viewCount++;
    }

    // 글 수정 메서드
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}