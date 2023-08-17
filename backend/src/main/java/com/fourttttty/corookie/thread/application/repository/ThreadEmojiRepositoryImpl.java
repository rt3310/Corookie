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
    public Optional<ThreadEmoji> findByMemberAndEmojiAndThread(Long memberId, Long emojiId, Long threadId) {
        return threadEmojiJpaRepository.findByMemberIdAndEmojiIdAndThreadId(memberId, emojiId, threadId);
    }

    @Override
    public Long countByEmojiAndThread(Long emojiId, Long threadId) {
        return threadEmojiJpaRepository.countByEmojiIdAndThreadId(emojiId, threadId);
    }

    @Override
    public Boolean existsByMemberAndEmojiAndThread(Long memberId, Long emojiId, Long threadId) {
        return threadEmojiJpaRepository.existsByMemberIdAndEmojiIdAndThreadId(memberId, emojiId, threadId);
    }

    @Override
    public void delete(ThreadEmoji threadEmoji) {
        threadEmojiJpaRepository.delete(threadEmoji);
    }

    @Override
    public void deleteById(Long id) {
        threadEmojiJpaRepository.deleteById(id);
    }
}
