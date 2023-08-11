package com.fourttttty.corookie.member.dto.response;

import com.fourttttty.corookie.member.domain.Member;

public record MemberResponse(String name,
                             String email) {

    public static MemberResponse from(Member member) {
        return new MemberResponse(member.getName(), member.getEmail());
    }
}
