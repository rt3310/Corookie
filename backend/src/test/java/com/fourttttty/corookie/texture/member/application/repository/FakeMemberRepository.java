package com.fourttttty.corookie.texture.member.application.repository;

import com.fourttttty.corookie.member.application.repository.MemberRepository;
import com.fourttttty.corookie.member.domain.Member;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeMemberRepository implements MemberRepository {
    private long autoIncrementId = 1L;
    private final Map<Long, Member> store = new HashMap<>();

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Member save(Member member) {
        store.put(autoIncrementId++, member);
        return member;
    }
}
