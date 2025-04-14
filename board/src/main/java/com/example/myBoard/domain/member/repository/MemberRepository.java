package com.example.myBoard.domain.member.repository;

import com.example.myBoard.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 이메일로 중복 여부 체크
    boolean existsByEmail(String email);

    // 이메일로 회원 조회 (로그인 시 사용)
    Optional<Member> findByEmail(String email);
}
