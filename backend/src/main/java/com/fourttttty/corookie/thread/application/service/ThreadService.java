package com.fourttttty.corookie.thread.application.service;

import com.fourttttty.corookie.member.application.service.MemberService;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.textchannel.application.service.TextChannelService;
import com.fourttttty.corookie.textchannel.domain.TextChannel;
import com.fourttttty.corookie.thread.application.repository.ThreadRepository;
import com.fourttttty.corookie.thread.domain.Thread;
import com.fourttttty.corookie.thread.dto.ThreadCreateRequest;
import com.fourttttty.corookie.thread.dto.ThreadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ThreadService {

    private final ThreadRepository threadRepository;
    private final TextChannelService textChannelService;
    private final MemberService memberService;

    public ThreadResponse createThread(ThreadCreateRequest request) {
        TextChannel textChannel = textChannelService.findEntityById(request.textChannelId());
        Member writer = memberService.findEntityById(request.writerId());
        Thread thread = Thread.create(request.content(), textChannel, writer, true);
        return new ThreadResponse(threadRepository.save(thread).getId());
    }
}
