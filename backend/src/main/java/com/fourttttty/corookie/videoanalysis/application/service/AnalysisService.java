package com.fourttttty.corookie.videoanalysis.application.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
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

    @Value("{$cloud.aws.s3.bucket}")
    private String bucket;

    public AnalysisResponse uploadAudio(MultipartFile audio) throws IOException {
        String fileName = audio.getOriginalFilename();
        String fileUrl = "https://" + bucket + "/test" +fileName;
        ObjectMetadata metaData = new ObjectMetadata();
        metaData.setContentType(audio.getContentType());
        metaData.setContentLength(audio.getSize());

        amazonS3Client.putObject(new PutObjectRequest( bucket,fileName, audio.getInputStream(), metaData)
            .withCannedAcl(CannedAccessControlList.PublicRead));
        return AnalysisResponse.from("OK");
    }
}
