package com.fourttttty.corookie.textchannel.presentation;

import com.fourttttty.corookie.textchannel.application.service.TextChannelService;
import com.fourttttty.corookie.textchannel.domain.TextChannel;
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
    public ResponseEntity<List<TextChannel>> textChannelList(@PathVariable Long projectId) {
        return ResponseEntity.ok(textChannelService.findAll());
    }

    // text channel 단일 조회
    @GetMapping("/{textChannelId}")
    public ResponseEntity<TextChannel> textChannelDetail(@PathVariable Long projectId, @PathVariable Long textChannelId) {
        return ResponseEntity.ok(textChannelService.findById(textChannelId));
    }

    @PostMapping("/")
    public ResponseEntity<TextChannel> textChannelCreation(@PathVariable Long projectId, @RequestParam(required = true) String name) {
        return ResponseEntity.ok(textChannelService.createTextChannel(name));
    }

}
