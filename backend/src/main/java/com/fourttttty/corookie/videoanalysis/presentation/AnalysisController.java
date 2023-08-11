package com.fourttttty.corookie.videoanalysis.presentation;

import com.fourttttty.corookie.videoanalysis.application.service.AnalysisService;
import com.fourttttty.corookie.videoanalysis.dto.AnalysisResponse;
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
    public ResponseEntity<AnalysisResponse> AnalysisCreate(
        @RequestParam("file")MultipartFile file,
        @PathVariable("videoChannelId") Long videoChannelId){
        return ResponseEntity.ok(analysisService.createAnalysis(file,videoChannelId));
    }
}
