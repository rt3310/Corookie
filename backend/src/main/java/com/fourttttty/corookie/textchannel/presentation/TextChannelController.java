package com.fourttttty.corookie.textchannel.presentation;

import com.fourttttty.corookie.textchannel.application.service.TextChannelService;
import com.fourttttty.corookie.textchannel.domain.TextChannel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/projects/{projectId}/text-channels")
public class TextChannelController {

    private final TextChannelService textChannelService;

    @GetMapping("/")
    public ResponseEntity<List<TextChannel>> textChannelList(@PathVariable Long projectId) {
        return ResponseEntity.ok(textChannelService.findAll());
    }
}
