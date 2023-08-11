package com.fourttttty.corookie.thread.dto.response;

import com.fourttttty.corookie.member.dto.response.MemberResponse;
import com.fourttttty.corookie.thread.domain.Thread;

import java.time.LocalDateTime;
import java.util.List;

public record ThreadListResponse(MemberResponse writer,
                                 LocalDateTime createdAt,
                                 String content,
                                 Integer commentCount) {

    public static ThreadListResponse from(Thread thread) {
        return new ThreadListResponse(
                MemberResponse.from(thread.getWriter()),
                thread.getCreatedAt(),
                thread.getContent(),
                thread.getCommentCount());
    }
}
