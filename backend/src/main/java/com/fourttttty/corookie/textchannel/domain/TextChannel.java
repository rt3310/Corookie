package com.fourttttty.corookie.textchannel.domain;

import com.fourttttty.corookie.global.audit.BaseTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "text_channel")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class TextChannel extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "channel_id")
    private Long id;

    private String channelName;
    private Boolean enabled;
    private Boolean deletable;

    public static TextChannel create(String name) {

        TextChannel newTextChannel = new TextChannel();
        newTextChannel.channelName = name;
        newTextChannel.enabled = true;

        return newTextChannel;
    }

    public void modifyChannelName(String name) {
        this.channelName = name;
    }

    public void deleteChannel() {
        this.deletable = false;
    }
}
