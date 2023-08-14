package com.fourttttty.corookie.videoanalysis.application.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fourttttty.corookie.videoanalysis.application.repository.AnalysisRepository;
import com.fourttttty.corookie.videoanalysis.application.repository.SttTokenRepository;
import com.fourttttty.corookie.videoanalysis.domain.Analysis;
import com.fourttttty.corookie.videoanalysis.domain.SttToken;
import com.fourttttty.corookie.videoanalysis.dto.AnalysisResponse;
import com.fourttttty.corookie.videochannel.application.repository.VideoChannelRepository;
import jakarta.persistence.EntityNotFoundException;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnalysisService {
    private final AmazonS3Client amazonS3Client;
    private final AnalysisRepository analysisRepository;
    private final SttTokenRepository sttTokenRepository;
    private final VideoChannelRepository videoChannelRepository;
    private final WebClient webClient;

    @Value("${cloud.aws.s3.bucket}")
    public String bucket;

    @Value("${web-client.vito-api.access-key}")
    public String accessKey;

    @Value("${web-client.vito-api.secret-access-key}")
    public String secretAccessKey;

    @Transactional
    public AnalysisResponse createAnalysis(MultipartFile file, Long videoChannelId)
        throws IOException {
        String s3URL, sttText, summarizeText;

        s3URL = uploadAudio(file);
        sttText = getSttText(file);
        summarizeText = summarizeText(sttText);

        Analysis analysis = analysisRepository.save(Analysis.of(s3URL,
            sttText,
            summarizeText,
            true,
            videoChannelRepository.findById(videoChannelId)
                .orElseThrow(EntityNotFoundException::new)
        ));

        return AnalysisResponse.from(analysis);
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


    public String getSttText(MultipartFile file) throws IOException {
        String transcribeId;
        SttToken sttToken;
        try{
            sttToken = (sttTokenRepository.findById(1L)
                .orElseThrow(EntityNotFoundException::new));
        }catch (EntityNotFoundException e){
            sttToken = sttTokenRepository.save(SttToken.of(getToken()));
        }

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file",Analysis.convertFileResource(file));
        body.add("config",Analysis.getSttConfig());

//        Mono<String> initResponse = webClient.post()
//            .uri("transcribe")
//            .contentType(MediaType.MULTIPART_FORM_DATA)
//            .body(BodyInserters.fromMultipartData(body))
//            .retrieve()
//            .bodyToMono(String.class);
        return "file";
    }

    public String getToken() {
        Mono<String> response = webClient.post()
            .uri("authenticate")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .bodyValue(
                new String("client_id=" + accessKey + "&client_secret=" + secretAccessKey))
            .retrieve()
            .bodyToMono(String.class);

        String token = response.flatMap(
            responseBody -> {
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode jsonNode = objectMapper.readTree(responseBody);
                    String accessToken = jsonNode.get("access_token").asText();

                    return Mono.just(accessToken);
                } catch (Exception e) {
                    return Mono.error(e);
                }
        }).block();

        return token;
    }

    public String summarizeText(String sttText) {

        return "";
    }

    public SttToken modifyToken(SttToken sttToken){
        return SttToken.modifyToken(sttToken, getToken());
    }
}
