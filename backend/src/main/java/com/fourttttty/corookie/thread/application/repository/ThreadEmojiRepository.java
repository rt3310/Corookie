package com.fourttttty.corookie.thread.application.repository;

import com.fourttttty.corookie.thread.domain.ThreadEmoji;

import java.util.Optional;

public interface ThreadEmojiRepository {
    ThreadEmoji save(ThreadEmoji threadEmoji);
    Optional<ThreadEmoji> findByEmojiAndThread(Long emojiId, Long threadId, Long memberId);
    Long countByEmojiAndThread(Long emojiId, Long threadId);
    Boolean existsById(Long threadEmojiId);
    void delete(Long threadEmojiId);
}
