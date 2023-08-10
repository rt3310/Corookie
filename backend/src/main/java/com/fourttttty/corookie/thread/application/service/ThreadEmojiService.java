package com.fourttttty.corookie.thread.application.service;

import com.fourttttty.corookie.thread.application.repository.ThreadEmojiRepository;
import com.fourttttty.corookie.thread.domain.Emoji;
import com.fourttttty.corookie.thread.domain.ThreadEmoji;
import com.fourttttty.corookie.thread.dto.request.ThreadEmojiCreateRequest;
import com.fourttttty.corookie.thread.dto.response.ThreadEmojiResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ThreadEmojiService {
    private final ThreadEmojiRepository threadEmojiRepository;

    @Transactional
    public ThreadEmojiResponse create(ThreadEmojiCreateRequest request, Long threadId, Long memberId) {
        Long emojiId = request.emojiId();

        ThreadEmoji threadEmoji  = threadEmojiRepository.save(ThreadEmoji.of(memberId, emojiId, threadId));
        Long count = threadEmojiRepository.countByEmojiAndThread(emojiId, threadId);
        return ThreadEmojiResponse.from(threadEmoji.getEmojiId(), true, count);
    }

    @Transactional
    public void delete(Long emojiId, Long threadId, Long memberId) {
        Long id = getThreadEmojiId(emojiId, threadId, memberId);
        threadEmojiRepository.delete(id);
    }

    private Long getThreadEmojiId(Long emojiId, Long threadId, Long memberId) {
        ThreadEmoji threadEmoji = threadEmojiRepository
                .findByEmojiAndThread(emojiId, threadId, memberId)
                .orElseThrow(EntityNotFoundException::new);
        return threadEmoji.getThreadEmojiId();
    }

    public List<ThreadEmojiResponse> findByThreadAndMember(Long threadId, Long memberId) {
        List<ThreadEmojiResponse> list = new ArrayList<>();

        for (Emoji emoji : Emoji.values()) {
            Long emojiId = emoji.getId();
            Long threadEmojiId = getThreadEmojiId(memberId, emojiId, threadId);
            Boolean isClicked = threadEmojiRepository.existsById(threadEmojiId);
            Long count = threadEmojiRepository.countByEmojiAndThread(emojiId, threadId);
            list.add(ThreadEmojiResponse.from(emojiId, isClicked, count));
        }
        return list;
    }
}
