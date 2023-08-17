package com.fourttttty.corookie.videoanalysis.dto;

import com.fourttttty.corookie.videoanalysis.domain.Analysis;

public record AnalysisResponse(Long id,
                               String s3URL,
                               String sttText,
                               String summarizationText) {

    public static AnalysisResponse from(Analysis analysis) {
        return new AnalysisResponse(analysis.getId(),
            analysis.getS3URL(),
            analysis.getSttText(),
            analysis.getSummarizationText());
    }
}
