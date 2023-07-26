package com.fourttttty.corookie.member.application.service;

import com.fourttttty.corookie.member.application.repository.MemberRepository;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.member.dto.request.MemberCreateRequest;
import com.fourttttty.corookie.member.dto.response.MemberResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    public Member findEntityById(Long id) {
        return memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public MemberResponse create(MemberCreateRequest memberCreateRequest) {
        return MemberResponse.of(memberRepository.save(memberCreateRequest.toEntity()));
    }
}
