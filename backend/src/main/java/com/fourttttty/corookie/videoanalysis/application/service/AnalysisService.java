package com.fourttttty.corookie.videoanalysis.application.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.fourttttty.corookie.videoanalysis.dto.AnalysisResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnalysisService {
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;

    @Transactional
    public AnalysisResponse createAnalysis(MultipartFile file, Long videoChannelId) {
        String s3URL, STTText, summarizeText;
        try {
            s3URL = uploadAudio(file);
        } catch (Exception e) {
            return AnalysisResponse.from("error");
        }
        return AnalysisResponse.from(s3URL);
    }

    public String uploadAudio(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();

        String fileUrl = "https://s3.ap-northeast-2.amazonaws.com/" + bucket + "/test" + fileName;
        ObjectMetadata metaData = new ObjectMetadata();
        metaData.setContentType(file.getContentType());
        metaData.setContentLength(file.getSize());

        amazonS3Client.putObject(bucket, fileName, file.getInputStream(), metaData);
        return fileUrl;
    }
}
