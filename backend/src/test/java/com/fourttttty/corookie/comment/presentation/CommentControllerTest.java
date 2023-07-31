package com.fourttttty.corookie.comment.presentation;

import com.fourttttty.corookie.comment.application.service.CommentService;
import com.fourttttty.corookie.comment.domain.Comment;
import com.fourttttty.corookie.comment.dto.request.CommentModifyRequest;
import com.fourttttty.corookie.comment.dto.response.CommentDetailResponse;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.member.dto.response.MemberResponse;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.support.RestDocsTest;
import com.fourttttty.corookie.textchannel.domain.TextChannel;
import com.fourttttty.corookie.thread.domain.Thread;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.List;

import static com.fourttttty.corookie.support.ApiDocumentUtils.getDocumentRequest;
import static com.fourttttty.corookie.support.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
class CommentControllerTest extends RestDocsTest {

    @MockBean
    private CommentService commentService;

    @MockBean
    private SimpMessageSendingOperations sendingOperations;

    private Member member;
    private Project project;
    private TextChannel textChannel;
    private Thread thread;
    private Comment comment;

    @BeforeEach
    void initTexture() {
        member = new Member("name");
        project = Project.of("project",
                "description",
                true,
                "http://test.com",
                false,
                member);
        textChannel = TextChannel.of("channelName",
                true,
                true,
                project);
        thread = Thread.of("content",
                true,
                0,
                textChannel,
                member);
        comment = Comment.of("content",
                true,
                thread,
                member);
    }

    @Test
    @DisplayName("댓글 전체 조회")
    void findAllComment() throws Exception {
        // given
        LocalDateTime now = LocalDateTime.now();
        CommentDetailResponse commentDetailResponse = new CommentDetailResponse(thread.getContent(),
                new MemberResponse(thread.getWriter().getName()),
                now
                );

        given(commentService.findAll(any(Long.class)))
                .willReturn(List.of(commentDetailResponse));

        // when
        ResultActions perform = mockMvc.perform(get("/api/v1/projects/{projectId}/text-channels/{textChannelId}/threads/{threadId}/comments", 1L, 1L, 1L));

        // then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value(commentDetailResponse.content()))
                .andExpect(jsonPath("$[0].writer.name").value(commentDetailResponse.writer().name()));

        perform.andDo(print())
                .andDo(document("comment-find-all",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("projectId").description("프로젝트 키"),
                                parameterWithName("textChannelId").description("텍스트 채널 키"),
                                parameterWithName("threadId").description("스레드 키")),
                        responseFields(
                                fieldWithPath("[].content").type(STRING).description("댓글 내용"),
                                fieldWithPath("[].createdAt").type(STRING).description("생성 일자"),
                                fieldWithPath("[].writer.name").type(STRING).description("작성자"))
                ));
    }

    @Test
    @DisplayName("댓글 수정")
    void modifyTextChannel() throws Exception {
        // given
        LocalDateTime now = LocalDateTime.now();
        CommentDetailResponse commentDetailResponse = new CommentDetailResponse(thread.getContent(),
                new MemberResponse(thread.getWriter().getName()),
                now
        );

        given(commentService.modify(any(CommentModifyRequest.class), any(Long.class)))
                .willReturn(commentDetailResponse);

        CommentModifyRequest request = new CommentModifyRequest("content");

        // when
        ResultActions perform = mockMvc.perform(put("/api/v1/projects/{projectId}/text-channels/{textChannelId}/threads/{threadId}/comments/{commentId}", 1L, 1L, 1L, 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)));;

        // then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(commentDetailResponse.content()))
                .andExpect(jsonPath("$.writer.name").value(commentDetailResponse.writer().name()));

        perform.andDo(print())
                .andDo(document("thread-modify",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("projectId").description("프로젝트 키"),
                                parameterWithName("textChannelId").description("텍스트 채널 키"),
                                parameterWithName("threadId").description("스레드 키"),
                                parameterWithName("commentId").description("댓글 키")),
                        responseFields(
                                fieldWithPath("content").type(STRING).description("댓글 내용"),
                                fieldWithPath("createdAt").type(STRING).description("생성 일자"),
                                fieldWithPath("writer.name").type(STRING).description("작성자"))
                ));
    }

    @Test
    @DisplayName("댓글 삭제")
    void deleteComment() throws Exception{
        // given

        // when
        ResultActions perform = mockMvc.perform(delete("/api/v1/projects/{projectId}/text-channels/{textChannelId}/threads/{threadId}/comments/{commentId}", 1L, 1L, 1L, 1L));

        // then
        perform.andExpect(status().isNoContent());

        perform.andDo(print())
                .andDo(document("comment-delete",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("projectId").description("프로젝트 키"),
                                parameterWithName("textChannelId").description("텍스트 채널 키"),
                                parameterWithName("threadId").description("스레드 키"),
                                parameterWithName("commentId").description("댓글 키"))));
    }
}