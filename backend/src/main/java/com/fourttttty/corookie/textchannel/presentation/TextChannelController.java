package com.fourttttty.corookie.textchannel.presentation;

import com.fourttttty.corookie.textchannel.application.service.TextChannelService;
import com.fourttttty.corookie.textchannel.dto.TextChannelResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/projects/{projectId}/text-channels")
public class TextChannelController {

    private final TextChannelService textChannelService;

    // text channel 전체 조회
    @GetMapping("/")
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
    @PostMapping("/")
    public ResponseEntity<TextChannelResponse> textChannelCreate(@PathVariable Long projectId,
                                                                 @RequestBody(required = true) String name) {
        return ResponseEntity.ok(textChannelService.createTextChannel(name, projectId));
    }

    // text channel 제목 수정
    @PutMapping("/{textChannelId}")
    public ResponseEntity<TextChannelResponse> textChannelModify(@PathVariable Long projectId,
                                                                 @PathVariable Long textChannelId,
                                                                 @RequestBody(required = true) String name) {
        return ResponseEntity.ok(textChannelService.modifyTextChannel(textChannelId, name));
    }

    @DeleteMapping("/{textChannelId}")
    public ResponseEntity<TextChannelResponse> textChannelDelete(@PathVariable Long projectId,
                                                                 @PathVariable Long textChannelId) {
        textChannelService.deleteTextChannel(textChannelId);
        return ResponseEntity.ok().build();
    }
}
