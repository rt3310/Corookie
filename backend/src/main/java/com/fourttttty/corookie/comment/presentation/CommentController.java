package com.fourttttty.corookie.comment.presentation;

import com.fourttttty.corookie.comment.application.service.CommentService;
import com.fourttttty.corookie.comment.dto.request.CommentCreateRequest;
import com.fourttttty.corookie.comment.dto.request.CommentModifyRequest;
import com.fourttttty.corookie.comment.dto.response.CommentDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/projects/{projectId}/text-channels/{textChannelId}/threads/{threadId}/comments")
public class CommentController {

    private final SimpMessageSendingOperations sendingOperations;
    private final CommentService commentService;

    // 특정 스레드 코멘트 전체 조회
    @GetMapping
    public ResponseEntity<List<CommentDetailResponse>> commentList(@PathVariable Long projectId,
                                                                   @PathVariable Long textChannelId,
                                                                   @PathVariable Long threadId) {
        return ResponseEntity.ok(commentService.findAll(threadId));
    }

    // 코멘트 추가
    @MessageMapping("/comment")
    public ResponseEntity<CommentDetailResponse> commentCreate(CommentCreateRequest request) {
        sendingOperations.convertAndSend("/topic/comment/" + request.threadId(), request);
        return ResponseEntity.ok(commentService.create(request, request.writerId()));
    }

    // 코멘트 수정
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDetailResponse> commentModify(@PathVariable Long projectId,
                                                              @PathVariable Long textChannelId,
                                                              @PathVariable Long threadId,
                                                              @PathVariable Long commentId,
                                                              CommentModifyRequest request) {
        return ResponseEntity.ok(commentService.modify(request, commentId));
    }

    // 코멘트 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> commentDelete(@PathVariable Long projectId,
                                             @PathVariable Long textChannelId,
                                             @PathVariable Long threadId,
                                             @PathVariable Long commentId) {
        commentService.delete(commentId);
        return ResponseEntity.noContent().build();
    }
}
