package com.fourttttty.corookie.thread.presentation;

import com.fourttttty.corookie.member.domain.AuthProvider;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.member.domain.Oauth2;
import com.fourttttty.corookie.member.dto.response.MemberResponse;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.support.RestDocsTest;
import com.fourttttty.corookie.textchannel.domain.TextChannel;
import com.fourttttty.corookie.thread.application.service.ThreadEmojiService;
import com.fourttttty.corookie.thread.application.service.ThreadService;
import com.fourttttty.corookie.thread.domain.Thread;
import com.fourttttty.corookie.thread.dto.request.ThreadModifyRequest;
import com.fourttttty.corookie.thread.dto.response.ThreadDetailResponse;
import com.fourttttty.corookie.thread.dto.response.ThreadEmojiResponse;
import com.fourttttty.corookie.thread.dto.response.ThreadListResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.fourttttty.corookie.support.ApiDocumentUtils.getDocumentRequest;
import static com.fourttttty.corookie.support.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.JsonFieldType.BOOLEAN;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ThreadController.class)
class ThreadControllerTest extends RestDocsTest {

    @MockBean
    private ThreadService threadService;

    @MockBean
    private SimpMessageSendingOperations sendingOperations;

    private Thread thread;
    private List<ThreadEmojiResponse> emojis;

    @BeforeEach
    void initTexture() {
        Member member = Member.of("name", "email", Oauth2.of(AuthProvider.KAKAO, "account"));
        Project project = Project.of("project",
                "description",
                true,
                "http://test.com",
                false,
                member);
        TextChannel textChannel = TextChannel.of("channelName",
                true,
                true,
                project);
        thread = Thread.of("content",
                true,
                0,
                textChannel,
                member);
        emojis = new ArrayList<>();
        emojis.add(ThreadEmojiResponse.from(1L, false, 1L));
    }

    @Test
    @DisplayName("스레드 전체 조회")
    void findAllThread() throws Exception {
        // given
        LocalDateTime now = LocalDateTime.now();
        ThreadListResponse threadListResponse = new ThreadListResponse(MemberResponse.from(thread.getWriter()),
                now,
                thread.getContent(),
                thread.getCommentCount()
                );

        given(threadService.findAll(any(Long.class)))
                .willReturn(List.of(threadListResponse));

        // when
        ResultActions perform = mockMvc.perform(get("/api/v1/projects/{projectId}/text-channels/{textChannelId}/threads", 1L, 1L));

        // then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value(threadListResponse.content()))
                .andExpect(jsonPath("$[0].commentCount").value(threadListResponse.commentCount()))
                .andExpect(jsonPath("$[0].writer.name").value(threadListResponse.writer().name()));

        perform.andDo(print())
                .andDo(document("thread-find-all",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("projectId").description("프로젝트 키"),
                                parameterWithName("textChannelId").description("텍스트 채널 키")),
                        responseFields(
                                fieldWithPath("[].content").type(STRING).description("스레드 내용"),
                                fieldWithPath("[].commentCount").type(NUMBER).description("댓글 수"),
                                fieldWithPath("[].createdAt").type(STRING).description("생성 일자"),
                                fieldWithPath("[].writer.name").type(STRING).description("작성자 이름"),
                                fieldWithPath("[].writer.email").type(STRING).description("작성자 이메일"))
                ));
    }

    @Test
    @DisplayName("스레드 상세 조회")
    void findThreadById() throws Exception {
        // given
        LocalDateTime now = LocalDateTime.now();

        ThreadDetailResponse threadDetailResponse = new ThreadDetailResponse(
                MemberResponse.from(thread.getWriter()),
                now,
                thread.getContent(),
                thread.getCommentCount(),
                emojis
        );

        given(threadService.findById(any(Long.class), any(Long.class)))
                .willReturn(threadDetailResponse);

        // when
        ResultActions perform = mockMvc.perform(get("/api/v1/projects/{projectId}/text-channels/{textChannelId}/threads/{threadId}", 1L, 1L, 1L));

        // then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(threadDetailResponse.content()))
                .andExpect(jsonPath("$.commentCount").value(threadDetailResponse.commentCount()))
                .andExpect(jsonPath("$.writer.name").value(threadDetailResponse.writer().name()));

        perform.andDo(print())
                .andDo(document("thread-find-by-id",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("projectId").description("프로젝트 키"),
                                parameterWithName("textChannelId").description("텍스트 채널 키"),
                                parameterWithName("threadId").description("스레드 키")),
                        responseFields(
                                fieldWithPath("content").type(STRING).description("스레드 내용"),
                                fieldWithPath("commentCount").type(NUMBER).description("댓글 수"),
                                fieldWithPath("createdAt").type(STRING).description("생성 일자"),
                                fieldWithPath("writer.name").type(STRING).description("작성자 이름"),
                                fieldWithPath("writer.email").type(STRING).description("작성자 이메일"),
                                fieldWithPath("emojis.[].emojiId").type(NUMBER).description("이모지 키"),
                                fieldWithPath("emojis.[].isClicked").type(BOOLEAN).description("회원의 이모지 클릭 여부"),
                                fieldWithPath("emojis.[].count").type(NUMBER).description("이모지 갯수")
                        )
                ));
    }

    @Test
    @DisplayName("스레드 수정")
    void modifyTextChannel() throws Exception {
        // given
        LocalDateTime now = LocalDateTime.now();

        ThreadDetailResponse threadDetailResponse = new ThreadDetailResponse(
                MemberResponse.from(thread.getWriter()),
                now,
                thread.getContent(),
                thread.getCommentCount(),
                emojis
        );

        given(threadService.modify(any(ThreadModifyRequest.class), any(Long.class), any(Long.class)))
                .willReturn(threadDetailResponse);

        ThreadModifyRequest request = new ThreadModifyRequest("content");

        // when
        ResultActions perform = mockMvc.perform(put("/api/v1/projects/{projectId}/text-channels/{textChannelId}/threads/{threadId}", 1L, 1L, 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)));

        // then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(threadDetailResponse.content()))
                .andExpect(jsonPath("$.commentCount").value(threadDetailResponse.commentCount()))
                .andExpect(jsonPath("$.writer.name").value(threadDetailResponse.writer().name()))
                .andExpect(jsonPath("$.emojis.[0].emojiId").value(threadDetailResponse.emojis().get(0).emojiId()));

        perform.andDo(print())
                .andDo(document("thread-modify",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("projectId").description("프로젝트 키"),
                                parameterWithName("textChannelId").description("텍스트 채널 키"),
                                parameterWithName("threadId").description("스레드 키")),
                        responseFields(
                                fieldWithPath("content").type(STRING).description("스레드 내용"),
                                fieldWithPath("commentCount").type(NUMBER).description("댓글 수"),
                                fieldWithPath("createdAt").type(STRING).description("생성 일자"),
                                fieldWithPath("writer.name").type(STRING).description("작성자 이름"),
                                fieldWithPath("writer.email").type(STRING).description("작성자 이메일"),
                                fieldWithPath("emojis.[].emojiId").type(NUMBER).description("이모지 키"),
                                fieldWithPath("emojis.[].isClicked").type(BOOLEAN).description("회원의 이모지 클릭 여부"),
                                fieldWithPath("emojis.[].count").type(NUMBER).description("이모지 갯수")
                        )
                ));
    }

    @Test
    @DisplayName("스레드 삭제")
    void deleteThread() throws Exception{
        // given

        // when
        ResultActions perform = mockMvc.perform(delete("/api/v1/projects/{projectId}/text-channels/{textChannelId}/threads/{threadId}", 1L, 1L, 1L));

        // then
        perform.andExpect(status().isNoContent());

        perform.andDo(print())
                .andDo(document("thread-delete",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("projectId").description("프로젝트 키"),
                                parameterWithName("textChannelId").description("텍스트 채널 키"),
                                parameterWithName("threadId").description("스레드 키"))));
    }


}



















