package com.example.myBoard.domain.member.mapper;

import com.example.myBoard.domain.member.dto.MemberJoinRequestDto;
import com.example.myBoard.domain.member.dto.MemberLoginRequestDto;
import com.example.myBoard.domain.member.dto.MemberResponseDto;
import com.example.myBoard.domain.member.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    // 회원가입 요청 DTO -> Member Entity (비밀번호는 이미 암호화된 상태여야 함)
    public Member toEntity(MemberJoinRequestDto dto, String encodedPassword) {
        return Member.builder()
                .email(dto.getEmail())
                .password(encodedPassword)
                .nickname(dto.getNickname())
                .role(Member.Role.USER) // 기본적으로 일반 유저로 가입
                .build();
    }

    // 로그인 요청 DTO -> Member (일반적으로 사용하지 않지만 예시용)
    public Member toEntity(MemberLoginRequestDto dto, String encodedPassword) {
        return Member.builder()
                .email(dto.getEmail())
                .password(encodedPassword)
                .build();
    }

    // Member Entity -> 응답 DTO
    public MemberResponseDto toDto(Member member) {
        return new MemberResponseDto(member);
    }
}
