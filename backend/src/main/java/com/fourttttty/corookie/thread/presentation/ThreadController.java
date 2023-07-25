package com.fourttttty.corookie.thread.presentation;

import com.fourttttty.corookie.thread.application.service.ThreadService;
import com.fourttttty.corookie.thread.dto.ThreadCreateRequest;
import com.fourttttty.corookie.thread.dto.ThreadResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ThreadController {

    private final ThreadService threadService;

    // 특정 브로커로 메시지 전달
    private final SimpMessageSendingOperations sendingOperations;

    // Client가 SEND할 수 있는 경로
    // stompConfig에서 설정한 apllicationDestinationPrefixes와
    // @MessageMapping 경로가 병합됨
    @MessageMapping("/thread")
    public ResponseEntity<ThreadResponse> threadCreate(ThreadCreateRequest request) {
        // 스레드 전달
        sendingOperations.convertAndSend("/topic/thread/" + request.textChannelId(), request);
        // 스레드 생성
        return ResponseEntity.ok(threadService.createThread(request));
    }

    // 스레드 삭제

    // 스레드 수정

    // 스레드 상세 보기

    // 특정 텍스트 채널의 스레드 전체 보기


}
