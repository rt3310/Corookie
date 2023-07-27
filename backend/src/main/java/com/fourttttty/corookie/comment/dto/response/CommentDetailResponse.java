package com.fourttttty.corookie.comment.dto.response;

import com.fourttttty.corookie.comment.domain.Comment;
import com.fourttttty.corookie.member.dto.response.MemberResponse;

import java.time.LocalDateTime;

public record CommentDetailResponse(String content,
                                    MemberResponse writer,
                                    LocalDateTime createdAt) {

    public static CommentDetailResponse of(Comment comment) {
        return new CommentDetailResponse(
                comment.getContent(),
                new MemberResponse(comment.getWriter().getName()),
                comment.getCreatedAt());
    }
}
