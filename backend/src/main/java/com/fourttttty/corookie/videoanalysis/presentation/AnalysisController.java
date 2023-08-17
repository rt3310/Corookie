package com.fourttttty.corookie.videoanalysis.presentation;

import com.fourttttty.corookie.videoanalysis.application.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/projects/{projectId}/video-channels/{videoChannelId}/analysis")
public class AnalysisController {
    private final AnalysisService analysisService;

    @PostMapping
    public ResponseEntity<Object> AnalysisCreate(
        @RequestParam MultipartFile file,
        @RequestParam String recordName,
        @PathVariable("videoChannelId") Long videoChannelId){
        try {
            return ResponseEntity.ok(analysisService.createAnalysis(file,recordName, videoChannelId));
        }catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }
}
