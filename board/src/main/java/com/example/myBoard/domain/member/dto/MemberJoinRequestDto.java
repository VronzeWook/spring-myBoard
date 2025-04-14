package com.example.myBoard.domain.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberJoinRequestDto {
    private String email;
    private String password;
    private String nickname;
}