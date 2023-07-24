package com.fourttttty.corookie.textchannel.presentation;

import com.fourttttty.corookie.textchannel.domain.Thread;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ThreadController {

    // 특정 브로커로 메시지 전달
    private final SimpMessageSendingOperations sendingOperations;

    // Client가 SEND할 수 있는 경로
    // stompConfig에서 설정한 apllicationDestinationPrefixes와
    // @MessageMapping 경로가 병합됨
    @MessageMapping("/templates.thread")
    public void message(Thread thread) {
        sendingOperations.convertAndSend("/topic/thread/text-channel/" + thread.getId(), thread);
    }
}
