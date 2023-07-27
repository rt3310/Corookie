package com.fourttttty.corookie.member.dto.request;

import com.fourttttty.corookie.member.domain.Member;

public record MemberCreateRequest(String name) {

    public Member toEntity() {
        return new Member(name);
    }
}
