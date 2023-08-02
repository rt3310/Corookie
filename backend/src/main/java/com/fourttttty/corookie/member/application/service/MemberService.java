package com.fourttttty.corookie.member.application.service;

import com.fourttttty.corookie.config.security.oauth2.OAuth2Request;
import com.fourttttty.corookie.member.application.repository.MemberRepository;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.member.domain.Oauth2;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    public Member save(OAuth2Request oAuth2Request) {
        return memberRepository.save(Member.of(oAuth2Request.getName(), oAuth2Request.getEmail(),
                Oauth2.of(oAuth2Request.getAuthProvider(), oAuth2Request.getAccountId())));
    }

    public Member findEntityById(Long id) {
        return memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Member saveIfNotExists(OAuth2Request oAuth2Request) {
        return memberRepository.findByOAuth2Account(oAuth2Request.getAccountId())
                .orElseGet(() -> save(oAuth2Request));
    }
}
