package com.fourttttty.corookie.thread.presentation;

import com.fourttttty.corookie.config.security.LoginUser;
import com.fourttttty.corookie.thread.application.service.ThreadEmojiService;
import com.fourttttty.corookie.thread.dto.request.ThreadEmojiRequest;
import com.fourttttty.corookie.thread.dto.response.ThreadEmojiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/projects/{projectId}/text-channels/{textChannelId}/threads/{threadId}/emojis")
public class ThreadEmojiController {

    private final ThreadEmojiService threadEmojiService;

    @GetMapping
    public ResponseEntity<List<ThreadEmojiResponse>> threadEmojiList(@PathVariable Long threadId,
                                                                     @AuthenticationPrincipal LoginUser loginUser){
        return ResponseEntity.ok(threadEmojiService.findByThreadAndMember(threadId, loginUser.getMemberId()));
    }

    @PostMapping
    public ResponseEntity<ThreadEmojiResponse> threadEmojiCreate(ThreadEmojiRequest request,
                                                                       @PathVariable Long threadId,
                                                                       @AuthenticationPrincipal LoginUser loginUser) {
        return ResponseEntity.ok(threadEmojiService.create(request, threadId, loginUser.getMemberId()));
    }

    @DeleteMapping
    public ResponseEntity<Void> threadEmojiDelete(ThreadEmojiRequest request,
                                                  @PathVariable Long threadId,
                                                  @AuthenticationPrincipal LoginUser loginUser){
        threadEmojiService.delete(request, threadId, loginUser.getMemberId());
        return ResponseEntity.noContent().build();
    }
}
