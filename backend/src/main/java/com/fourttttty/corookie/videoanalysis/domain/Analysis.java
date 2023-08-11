package com.fourttttty.corookie.videoanalysis.domain;

import com.fourttttty.corookie.global.audit.BaseTime;
import com.fourttttty.corookie.videochannel.domain.VideoChannel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "analysis")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Analysis extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "analysis_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String s3URL;

    @Column(nullable = false,columnDefinition = "text")
    private String STTText;

    @Column(nullable = false,columnDefinition = "text")
    private String summarizationText;

    @Column(nullable = false,columnDefinition = "text")
    private Boolean enabled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_channel_id")
    private VideoChannel videoChannel;

    private Analysis(String s3URL,
        String STTText,
        String summarizationText,
        Boolean enabled,
        VideoChannel videoChannel){
        this.s3URL = s3URL;
        this.STTText = STTText;
        this.summarizationText = summarizationText;
        this.enabled = enabled;
        this.videoChannel = videoChannel;
    }

    public static Analysis of(String s3URL,
        String STTText,
        String summarizationText,
        Boolean enabled,
        VideoChannel videoChannel){
        return new Analysis(s3URL,STTText,summarizationText,enabled,videoChannel);
    }
    public void delete(){ this.enabled = false; }
}
