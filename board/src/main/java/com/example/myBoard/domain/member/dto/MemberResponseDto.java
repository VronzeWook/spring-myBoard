package com.example.myBoard.domain.member.dto;

import com.example.myBoard.domain.member.entity.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {
    private final Long id;
    private final String email;
    private final String nickname;
    private final boolean isAdmin;

    public MemberResponseDto(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.isAdmin = member.isAdmin();
    }
}