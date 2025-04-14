package com.example.myBoard.domain.member.service;

import com.example.myBoard.domain.member.dto.MemberJoinRequestDto;
import com.example.myBoard.domain.member.dto.MemberLoginRequestDto;

public interface MemberService {

    /**
     * 회원 가입
     * @param dto 회원 가입 요청 DTO
     * @return 생성된 회원 ID
     */
    Long join(MemberJoinRequestDto dto);

    /**
     * 로그인
     * @param dto 로그인 요청 DTO
     */
    void login(MemberLoginRequestDto dto);

    /**
     * 로그아웃
     */
    void logout();

    /**
     * 회원 탈퇴
     * @param memberId 탈퇴할 회원 ID
     * @param password 탈퇴 시 비밀번호 확인
     */
    void delete(Long memberId, String password);
}
