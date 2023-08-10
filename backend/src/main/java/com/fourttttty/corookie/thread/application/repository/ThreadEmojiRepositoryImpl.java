package com.fourttttty.corookie.thread.application.repository;

import com.fourttttty.corookie.thread.domain.ThreadEmoji;
import com.fourttttty.corookie.thread.infrastructure.ThreadEmojiJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ThreadEmojiRepositoryImpl implements ThreadEmojiRepository {
    private final ThreadEmojiJpaRepository threadEmojiJpaRepository;

    @Override
    public ThreadEmoji save(ThreadEmoji threadEmoji) {
        return threadEmojiJpaRepository.save(threadEmoji);
    }

    @Override
    public Optional<ThreadEmoji> findByEmojiAndThread(Long emojiId, Long threadId, Long memberId) {
        return threadEmojiJpaRepository.findByMemberIdAndEmojiIdAndThreadId(emojiId, threadId, memberId);
    }

    @Override
    public Long countByEmojiAndThread(Long emojiId, Long threadId) {
        return threadEmojiJpaRepository.countByEmojiIdAndThreadId(emojiId, threadId);
    }

    @Override
    public Boolean existsById(Long threadEmojiId) {
        return threadEmojiJpaRepository.existsById(threadEmojiId);
    }

    @Override
    public void delete(Long threadEmojiId) {
        threadEmojiJpaRepository.deleteById(threadEmojiId);
    }
}
