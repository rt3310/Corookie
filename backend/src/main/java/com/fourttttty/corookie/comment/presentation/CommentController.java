package com.fourttttty.corookie.comment.presentation;

import com.fourttttty.corookie.comment.application.service.CommentService;
import com.fourttttty.corookie.comment.dto.CommentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/projects/{projectId}/text-channels/{textChannelId}/threads/{threadId}/comments")
public class CommentController {

    private final CommentService commentService;

    // 특정 스레드 코멘트 전체 조회
    @GetMapping("/")
    public ResponseEntity<CommentResponse> commentList(@PathVariable Long textChannelId, @PathVariable Long threadId) {
        return ResponseEntity.ok(commentService.findAll());
    }

    // 코멘트 추가

    // 코멘트 수정

    // 코멘트 삭제

}
