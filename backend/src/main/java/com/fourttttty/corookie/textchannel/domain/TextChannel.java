package com.fourttttty.corookie.textchannel.domain;

import com.fourttttty.corookie.global.audit.BaseTime;
import com.fourttttty.corookie.project.domain.Project;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "text_channel")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class TextChannel extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "channel_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String channelName;

    @Column(nullable = false)
    private Boolean enabled;

    @Column(nullable = false)
    private Boolean deletable;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    public static TextChannel create(String channelName,
                                     Boolean enabled,
                                     Project project) {
        TextChannel newTextChannel = new TextChannel();
        newTextChannel.channelName = channelName;
        newTextChannel.enabled = enabled;
        newTextChannel.deletable = true;
        newTextChannel.project = project;

        return newTextChannel;
    }

    public void changeNotDeletableChannel() {
        this.deletable = false;
    }

    public void modifyChannelName(String name) {
        this.channelName = name;
    }
}
