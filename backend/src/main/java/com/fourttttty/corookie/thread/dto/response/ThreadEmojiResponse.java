package com.fourttttty.corookie.thread.dto.response;

public record ThreadEmojiResponse(Long emojiId, Boolean isClicked, Long count) {
    public static ThreadEmojiResponse from(Long emojiId, Boolean isClicked, Long count) {
        return new ThreadEmojiResponse(emojiId, isClicked, count);
    }
}
