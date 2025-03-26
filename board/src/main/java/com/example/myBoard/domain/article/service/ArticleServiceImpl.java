package com.example.myBoard.domain.article.service;

import com.example.myBoard.domain.article.dto.ArticleRequestDto;
import com.example.myBoard.domain.article.dto.ArticleResponseDto;
import com.example.myBoard.domain.article.entity.Article;
import com.example.myBoard.domain.article.mapper.ArticleMapper;
import com.example.myBoard.domain.article.repository.ArticleRepository;
import com.example.myBoard.global.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Long createArticle(ArticleRequestDto dto) {
        // 게시물 생성 후 저장 (저장 시 ID 생성)
        Article article = articleMapper.toEntity(dto);
        Article saved = articleRepository.save(article); // 실제 저장된 객체를 반환
        return saved.getId();
    }

    @Override
    public ArticleResponseDto getArticle(Long id, HttpServletRequest request, HttpServletResponse response) {
        // 게시물이 존재하는지 확인 및 조회
        Article article = articleRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        // 조회수 증가 (쿠키로 중복 방지)
        String cookieName = "viewed_" + id;
        if(!CookieUtil.hasCookie(request, cookieName)) {
            article.increaseViewCount();
            articleRepository.save(article);
            CookieUtil.addCookie(response, cookieName, "true", 60 * 30);
        }

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
    public void updateArticle(Long id, ArticleRequestDto dto) {
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
    public void deleteArticle(Long id, String inputPassword) {
        // 게시물이 존재하는지 확인
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        // 비밀번호가 일치하는지 확인
        if(!passwordEncoder.matches(inputPassword, article.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 삭제 실행
        articleRepository.delete(article);
    }
}