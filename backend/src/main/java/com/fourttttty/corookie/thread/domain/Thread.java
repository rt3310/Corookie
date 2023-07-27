package com.fourttttty.corookie.thread.domain;

import com.fourttttty.corookie.global.audit.BaseTime;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.textchannel.domain.TextChannel;
import com.fourttttty.corookie.thread.dto.request.ThreadModifyRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Thread extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thread_id")
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Boolean enabled;

    @Column(nullable = false)
    private Integer commentCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_Id")
    private TextChannel textChannel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member writer;

    public static Thread create(String content, TextChannel textChannel, Member writer, Boolean enabled) {
        Thread thread = new Thread();
        thread.content = content;
        thread.textChannel = textChannel;
        thread.writer = writer;
        thread.enabled = enabled;
        thread.commentCount = 0;

        return thread;
    }

    public void delete() {
        this.enabled = false;
    }

    public void modify(ThreadModifyRequest request) {
        this.content = request.content();
    }

}
