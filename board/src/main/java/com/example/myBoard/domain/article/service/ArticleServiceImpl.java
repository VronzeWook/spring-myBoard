package com.example.myBoard.domain.article.service;

import com.example.myBoard.domain.article.dto.ArticleDeleteRequestDto;
import com.example.myBoard.domain.article.dto.ArticleCreateRequestDto;
import com.example.myBoard.domain.article.dto.ArticleResponseDto;
import com.example.myBoard.domain.article.dto.ArticleUpdateRequestDto;
import com.example.myBoard.domain.article.entity.Article;
import com.example.myBoard.domain.article.mapper.ArticleMapper;
import com.example.myBoard.domain.article.repository.ArticleRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Long createArticle(ArticleCreateRequestDto dto) {
        // 게시물 생성 후 저장 (저장 시 ID 생성)
        Article article = articleMapper.toEntity(dto);
        Article saved = articleRepository.save(article); // 실제 저장된 객체를 반환
        return saved.getId();
    }

    @Override
    public ArticleResponseDto getArticleDetail(Long id) {
        // 게시물이 존재하는지 확인 및 조회
        Article article = articleRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        return articleMapper.toDto(article);
    }

    @Override
    public List<ArticleResponseDto> getArticleList() {
        // Entity List를 DTO로 mapping 하여 반환
        return articleRepository.findAll().stream()
                // .map(article -> articleMapper.toDto(article))
                .map(articleMapper::toDto) // Lambda 축약식
                .toList();
    }

    @Override
    public void updateArticle(Long id, ArticleUpdateRequestDto dto) {
        // 게시글이 존재하는지 확인
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        // 비밀번호가 일치하는지 확인
        if(!passwordEncoder.matches(dto.getPassword(), article.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 업데이트 실행
        article.update(dto.getTitle(), dto.getContent());
    }

    @Override
    public void deleteArticle(Long id, ArticleDeleteRequestDto dto) {
        // 게시물이 존재하는지 확인
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        // 비밀번호가 일치하는지 확인
        if(!passwordEncoder.matches(dto.getPassword(), article.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 삭제 실행
        articleRepository.delete(article);
    }

    @Transactional
    @Override
    public void increaseViewCount(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        article.increaseViewCount();
    }
}