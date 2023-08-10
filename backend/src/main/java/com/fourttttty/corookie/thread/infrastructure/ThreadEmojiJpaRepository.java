package com.fourttttty.corookie.thread.infrastructure;

import com.fourttttty.corookie.thread.domain.ThreadEmoji;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ThreadEmojiJpaRepository extends JpaRepository<ThreadEmoji, Long> {
    Optional<ThreadEmoji> findByMemberIdAndEmojiIdAndThreadId(Long memberId, Long emojiId, Long threadId);
    Boolean existsByMemberIdAndEmojiIdAndThreadId(Long memberId, Long emojiId, Long threadId);
    Long countByEmojiIdAndThreadId(Long emojiId, Long threadId);
}
