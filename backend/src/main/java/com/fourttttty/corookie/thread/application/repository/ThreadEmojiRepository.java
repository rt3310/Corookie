package com.fourttttty.corookie.thread.application.repository;

import com.fourttttty.corookie.thread.domain.ThreadEmoji;

import java.util.Optional;

public interface ThreadEmojiRepository {
    ThreadEmoji save(ThreadEmoji threadEmoji);
    Optional<ThreadEmoji> findByMemberAndEmojiAndThread(Long memberId, Long emojiId, Long threadId);
    Long countByEmojiAndThread(Long emojiId, Long threadId);
    Boolean existsByMemberAndEmojiAndThread(Long memberId, Long emojiId, Long threadId);
    void delete(ThreadEmoji threadEmoji);
    void deleteById(Long id);
}
