package com.fourttttty.corookie.thread.dto.request;

import jakarta.validation.constraints.NotNull;

public record ThreadEmojiCreateRequest(@NotNull Long emojiId) {
}
