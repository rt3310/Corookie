package com.fourttttty.corookie.thread.application.service;

import com.fourttttty.corookie.member.application.service.MemberService;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.member.dto.response.MemberResponse;
import com.fourttttty.corookie.textchannel.application.service.TextChannelService;
import com.fourttttty.corookie.textchannel.domain.TextChannel;
import com.fourttttty.corookie.thread.application.repository.ThreadRepository;
import com.fourttttty.corookie.thread.domain.Thread;
import com.fourttttty.corookie.thread.dto.request.ThreadCreateRequest;
import com.fourttttty.corookie.thread.dto.request.ThreadModifyRequest;
import com.fourttttty.corookie.thread.dto.response.ThreadDetailResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ThreadService {

    private final ThreadRepository threadRepository;
    private final TextChannelService textChannelService;
    private final MemberService memberService;

    @Transactional
    public ThreadDetailResponse create(ThreadCreateRequest request, Long writerId) {
        TextChannel textChannel = textChannelService.findEntityById(request.textChannelId());
        Member writer = memberService.findEntityById(writerId);
        Thread thread = Thread.create(request.content(), textChannel, writer, true);
        threadRepository.save(thread);
        return ThreadDetailResponse.of(thread);
    }

    public Thread findEntityById(Long threadId) {
        return threadRepository.findById(threadId).orElseThrow(EntityNotFoundException::new);
    }

    public ThreadDetailResponse findById(Long threadId) {
        Thread thread = threadRepository.findById(threadId).orElseThrow(EntityNotFoundException::new);
        return ThreadDetailResponse.of(thread);
    }

    public List<ThreadDetailResponse> findAll(Long TextChannelId) {
        return threadRepository.findAll(TextChannelId)
                .stream()
                .map(ThreadDetailResponse::of)
                .toList();
    }

    @Transactional
    public void delete(Long threadId) {
        Thread thread = threadRepository.findById(threadId).orElseThrow(EntityNotFoundException::new);
        thread.delete();
    }

    @Transactional
    public ThreadDetailResponse modify(ThreadModifyRequest request, Long threadId) {
        Thread thread = threadRepository.findById(threadId).orElseThrow(EntityNotFoundException::new);
        thread.modify(request);
        return ThreadDetailResponse.of(thread);
    }
}
