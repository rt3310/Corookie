package com.fourttttty.corookie.thread.dto.response;

import com.fourttttty.corookie.member.dto.response.MemberResponse;
import com.fourttttty.corookie.thread.domain.Thread;

import java.time.LocalDateTime;

public record ThreadDetailResponse(MemberResponse writer,
                                   LocalDateTime createdAt,
                                   String content,
                                   Integer commentCount) {

    public static ThreadDetailResponse from(Thread thread) {
        return new ThreadDetailResponse(
                MemberResponse.from(thread.getWriter()),
                thread.getCreatedAt(),
                thread.getContent(),
                thread.getCommentCount());
    }
}
