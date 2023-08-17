package com.fourttttty.corookie.thread.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "thread_emoji")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ThreadEmoji {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thread_emoji_id")
    private Long id;

    @Column(name = "memeber_id")
    private Long memberId;

    @Column(name = "emoji_id")
    private Long emojiId;

    @Column(name = "thread_id")
    private Long threadId;

    private ThreadEmoji(Long memberId, Long emojiId, Long threadId) {
        this.memberId = memberId;
        this.emojiId = emojiId;
        this.threadId = threadId;
    }

    public static ThreadEmoji of(Long memberId, Long emojiId, Long threadId) {
        return new ThreadEmoji(memberId, emojiId, threadId);
    }

}
