package com.example.myBoard.domain.article.repository;

import com.example.myBoard.domain.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}