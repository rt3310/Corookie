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
    @Column(name = "channel_id")
    private Long id;

    private String channelName;
    private boolean enabled;
    private boolean deletable;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public static TextChannel create(String name, Project project) {

        TextChannel newTextChannel = new TextChannel();
        newTextChannel.channelName = name;
        newTextChannel.enabled = true;
        newTextChannel.project = project;

        return newTextChannel;
    }

    public void modifyChannelName(String name) {
        this.channelName = name;
    }
}
