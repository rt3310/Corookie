package com.fourttttty.corookie.textchannel.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "text_channel")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TextChannel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "channel_id")
    private Long id;

    private String channelName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean enabled;



}
