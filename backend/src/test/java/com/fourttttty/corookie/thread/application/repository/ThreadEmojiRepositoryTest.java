package com.fourttttty.corookie.thread.application.repository;

import com.fourttttty.corookie.config.audit.JpaAuditingConfig;
import com.fourttttty.corookie.member.domain.AuthProvider;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.member.domain.Oauth2;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.support.TestConfig;
import com.fourttttty.corookie.textchannel.domain.TextChannel;
import com.fourttttty.corookie.thread.domain.ThreadEmoji;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.fourttttty.corookie.thread.domain.Thread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({JpaAuditingConfig.class, TestConfig.class})
class ThreadEmojiRepositoryTest {
    @Autowired
    ThreadEmojiRepository threadEmojiRepository;

    private Member member;
    private Project project;
    private TextChannel textChannel;
    private Thread thread;

    @BeforeEach
    void initObjects() {
        member = Member.of("name", "email", Oauth2.of(AuthProvider.KAKAO, "account"));
        project = Project.of("memberName",
                "description",
                Boolean.FALSE,
                "http://test.com",
                Boolean.FALSE,
                member);
        textChannel = textChannel.of("channel name", true, true, project);
        thread = Thread.of("content",
                Boolean.TRUE,
                0,
                textChannel,
                member);
    }

    @Test
    @DisplayName("쓰레드 이모지 저장 테스트")
    void save() {
        // given
        ThreadEmoji threadEmoji = ThreadEmoji.of(1L, 1L, 1L);

        // when
        ThreadEmoji savedThreadEmoji = threadEmojiRepository.save(threadEmoji);

        // then
        assertThat(savedThreadEmoji.getEmojiId()).isEqualTo(threadEmoji.getEmojiId());
        assertThat(savedThreadEmoji.getMemberId()).isEqualTo(threadEmoji.getMemberId());
        assertThat(savedThreadEmoji.getThreadId()).isEqualTo(threadEmoji.getThreadId());
    }

    @Test
    @DisplayName("쓰레드별 이모지 정보 조회 테스트")
    void findByEmojiAndThread() {
        // given
        Long memberId = 1L;
        Long emojiId = 1L;
        Long threadId = 1L;

        ThreadEmoji threadEmoji = ThreadEmoji.of(memberId, emojiId, threadId);
        threadEmojiRepository.save(threadEmoji);

        // when
        Optional<ThreadEmoji> foundThreadEmoji = threadEmojiRepository.findByEmojiAndThread(emojiId, threadId, memberId);

        // then
        assertThat(foundThreadEmoji).isPresent();
        assertThat(foundThreadEmoji.get().getMemberId()).isEqualTo(memberId);
        assertThat(foundThreadEmoji.get().getEmojiId()).isEqualTo(emojiId);
        assertThat(foundThreadEmoji.get().getThreadId()).isEqualTo(threadId);
    }

    @Test
    @DisplayName("쓰레드별 이모지 갯수 조회 테스트")
    void countByEmojiAndThread() {
        // given


        // when


        // then

    }
    /*
    Long countByEmojiAndThread(Long emojiId, Long threadId);
    Boolean existsById(Long threadEmojiId);
    void delete(Long threadEmojiId);
    * */
}
