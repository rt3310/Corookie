package com.fourttttty.corookie.textchannel.presentation;

import com.fourttttty.corookie.textchannel.application.service.TextChannelService;
import com.fourttttty.corookie.textchannel.dto.request.TextChannelCreateRequest;
import com.fourttttty.corookie.textchannel.dto.response.TextChannelResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/projects/{projectId}/text-channels")
public class TextChannelController {

    private final TextChannelService textChannelService;

    // text channel 전체 조회
    @GetMapping
    public ResponseEntity<List<TextChannelResponse>> textChannelList(@PathVariable Long projectId) {
        return ResponseEntity.ok(textChannelService.findAll());
    }

    // text channel 단일 조회
    @GetMapping("/{textChannelId}")
    public ResponseEntity<TextChannelResponse> textChannelDetail(@PathVariable Long projectId,
                                                                 @PathVariable Long textChannelId) {
        return ResponseEntity.ok(textChannelService.findById(textChannelId));
    }

    // text channel 등록
    @PostMapping
    public ResponseEntity<TextChannelResponse> textChannelCreate(@PathVariable Long projectId,
                                                                 @RequestBody TextChannelCreateRequest request) {
        return ResponseEntity.ok(textChannelService.create(request, projectId));
    }

    // text channel 제목 수정
    @PutMapping("/{textChannelId}")
    public ResponseEntity<TextChannelResponse> textChannelModify(@PathVariable Long projectId,
                                                                 @PathVariable Long textChannelId,
                                                                 @RequestBody String name) {
        return ResponseEntity.ok(textChannelService.modify(textChannelId, name));
    }

    @DeleteMapping("/{textChannelId}")
    public ResponseEntity<Object> textChannelDelete(@PathVariable Long projectId,
                                                 @PathVariable Long textChannelId) {
        textChannelService.delete(textChannelId);
        return ResponseEntity.noContent().build();
    }
}
