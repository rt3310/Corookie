package com.fourttttty.corookie.thread.dto;

public record ThreadCreateRequest(
        Long textChannelId,
        Long writerId,
        String content
) {
}
