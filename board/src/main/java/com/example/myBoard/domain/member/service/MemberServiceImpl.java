package com.example.myBoard.domain.member.service;

import com.example.myBoard.domain.member.dto.MemberJoinRequestDto;
import com.example.myBoard.domain.member.dto.MemberLoginRequestDto;
import com.example.myBoard.domain.member.entity.Member;
import com.example.myBoard.domain.member.mapper.MemberMapper;
import com.example.myBoard.domain.member.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final HttpSession session;

    @Override
    public Long join(MemberJoinRequestDto dto) {
        // 이메일 중복 체크
        if (memberRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        // 매핑
        Member member = memberMapper.toEntity(dto, encodedPassword);
        return memberRepository.save(member).getId();
    }

    @Override
    public void login(MemberLoginRequestDto dto) {
        Member member = memberRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

        if (!passwordEncoder.matches(dto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        session.setAttribute("loginMember", member.getId());
    }

    @Override
    public void logout() {
        session.invalidate();
    }

    @Override
    public void delete(Long memberId, String password) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        memberRepository.delete(member);
        logout(); // 탈퇴 후 세션 종료
    }
}
