package com.fourttttty.corookie.member.dto.response;

import com.fourttttty.corookie.member.domain.Member;

public record MemberResponse(String name) {

    public static MemberResponse of(Member member) {
        return new MemberResponse(member.getName());
    }
}
