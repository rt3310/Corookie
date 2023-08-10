package com.fourttttty.corookie.videoanalysis.dto;

public record AnalysisResponse(String content) {
    public static AnalysisResponse from(String content){
        return new AnalysisResponse(content);
    }
}
