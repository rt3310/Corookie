package com.fourttttty.corookie.comment.dto;

import com.fourttttty.corookie.member.dto.response.MemberResponse;

import java.time.LocalDateTime;

public record CommentResponse(String content,
                              MemberResponse writer,
                              LocalDateTime createdAt) {
}
