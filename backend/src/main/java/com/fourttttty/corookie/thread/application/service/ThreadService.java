package com.fourttttty.corookie.thread.application.service;

import com.fourttttty.corookie.member.application.service.MemberService;
import com.fourttttty.corookie.textchannel.application.repository.TextChannelRepository;
import com.fourttttty.corookie.thread.application.repository.ThreadRepository;
import com.fourttttty.corookie.thread.domain.Thread;
import com.fourttttty.corookie.thread.dto.request.ThreadCreateRequest;
import com.fourttttty.corookie.thread.dto.request.ThreadModifyRequest;
import com.fourttttty.corookie.thread.dto.response.ThreadDetailResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ThreadService {

    private final ThreadRepository threadRepository;
    private final TextChannelRepository textChannelRepository;
    private final MemberService memberService;

    @Transactional
    public ThreadDetailResponse create(ThreadCreateRequest request) {
        Thread thread = Thread.of(request.content(), true, 0,
                textChannelRepository.findById(request.textChannelId()).orElseThrow(EntityNotFoundException::new),
                memberService.findEntityById(request.writerId()));
        return ThreadDetailResponse.from(threadRepository.save(thread));
    }

    public Thread findEntityById(Long threadId) {
        return threadRepository.findById(threadId).orElseThrow(EntityNotFoundException::new);
    }

    public ThreadDetailResponse findById(Long threadId) {
        return ThreadDetailResponse.from(threadRepository.findById(threadId)
                .orElseThrow(EntityNotFoundException::new));
    }

    public List<ThreadDetailResponse> findByTextChannelIdLatest(Long TextChannelId, Pageable pageable) {
        return threadRepository.findByTextChannelIdLatest(TextChannelId, pageable).stream()
                .map(ThreadDetailResponse::from)
                .toList();
    }

    @Transactional
    public void delete(Long threadId) {
        threadRepository.findById(threadId).orElseThrow(EntityNotFoundException::new).delete();
    }

    @Transactional
    public ThreadDetailResponse modify(ThreadModifyRequest request, Long threadId) {
        Thread thread = threadRepository.findById(threadId).orElseThrow(EntityNotFoundException::new);
        thread.modify(request);
        return ThreadDetailResponse.from(thread);
    }
}
