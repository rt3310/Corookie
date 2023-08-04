package com.fourttttty.corookie.thread.presentation;

import com.fourttttty.corookie.thread.application.service.ThreadService;
import com.fourttttty.corookie.thread.dto.request.ThreadCreateRequest;
import com.fourttttty.corookie.thread.dto.request.ThreadModifyRequest;
import com.fourttttty.corookie.thread.dto.response.ThreadDetailResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/projects/{projectId}/text-channels/{textChannelId}/threads")
public class ThreadController {

    private final ThreadService threadService;

    // 특정 브로커로 메시지 전달
    private final SimpMessageSendingOperations sendingOperations;

    // Client가 SEND할 수 있는 경로
    // stompConfig에서 설정한 apllicationDestinationPrefixes와
    // @MessageMapping 경로가 병합됨
    @MessageMapping("/thread")
    public ResponseEntity<ThreadDetailResponse> threadCreate(ThreadCreateRequest request) {
        // 스레드 전달
        sendingOperations.convertAndSend("/topic/thread/" + request.textChannelId(), request);
        // 스레드 생성
        return ResponseEntity.ok(threadService.create(request, request.writerId()));
    }

    // 스레드 상세 보기
    @GetMapping("/{threadId}")
    public ResponseEntity<ThreadDetailResponse> threadDetail(@PathVariable Long projectId,
                                                         @PathVariable Long textChannelId,
                                                         @PathVariable Long threadId) {
        return ResponseEntity.ok(threadService.findById(threadId));
    }

    // 특정 텍스트 채널의 스레드 전체 보기
    @GetMapping
    public ResponseEntity<List<ThreadDetailResponse>> threadList(@PathVariable Long projectId,
                                                                 @PathVariable Long textChannelId) {
        return ResponseEntity.ok(threadService.findAll(textChannelId));
    }

    // 스레드 삭제
    @DeleteMapping("/{threadId}")
    public ResponseEntity<Void> threadDelete(@PathVariable Long projectId,
                                             @PathVariable Long textChannelId,
                                             @PathVariable Long threadId) {
        threadService.delete(threadId);
        return ResponseEntity.noContent().build();
    }

    // 스레드 수정
    @PutMapping("/{threadId}")
    public ResponseEntity<ThreadDetailResponse> threadModify(@PathVariable Long projectId,
                                                             @PathVariable Long textChannelId,
                                                             @PathVariable Long threadId,
                                                             @RequestBody ThreadModifyRequest request) {
        return ResponseEntity.ok(threadService.modify(request, threadId));
    }
}
