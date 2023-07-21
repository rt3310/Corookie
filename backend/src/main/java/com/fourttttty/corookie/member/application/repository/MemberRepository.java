package com.fourttttty.corookie.member.application.repository;


import com.fourttttty.corookie.member.domain.Member;

import java.util.Optional;

public interface MemberRepository {
    Optional<Member> findById(Long id);
}
