package com.fourttttty.corookie.project.presentation;

import com.fourttttty.corookie.member.domain.AuthProvider;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.member.domain.Oauth2;
import com.fourttttty.corookie.member.dto.response.MemberResponse;
import com.fourttttty.corookie.project.application.service.ProjectMemberService;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.project.domain.ProjectMember;
import com.fourttttty.corookie.project.dto.request.ProjectMemberCreateRequest;
import com.fourttttty.corookie.project.dto.response.ProjectMemberResponse;
import com.fourttttty.corookie.support.RestDocsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import javax.xml.transform.Result;

import java.util.List;

import static com.fourttttty.corookie.support.ApiDocumentUtils.getDocumentRequest;
import static com.fourttttty.corookie.support.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProjectMemberController.class)
public class ProjectMemberControllerTest extends RestDocsTest {
    @MockBean
    private ProjectMemberService projectMemberService;

    private Project project;
    private Member member;

    @BeforeEach
    void initTexture() {
        project = Project.of("name", "description", true,
                "http://test.com", false, member);
        member = Member.of("name", "test@gmail.com", Oauth2.of(AuthProvider.KAKAO, "account"));
    }

    @Test
    @DisplayName("프로젝트에 회원을 등록한다")
    void projectMemberCreate() throws Exception {
        // given
        given(projectMemberService.createIfNone(any(ProjectMemberCreateRequest.class)))
                .willReturn(ProjectMemberResponse.from(ProjectMember.of(project, member)));

        MemberResponse memberResponse = MemberResponse.of(member);
        ProjectMemberResponse response = ProjectMemberResponse.from(ProjectMember.of(project, member));

        Long projectId = 1L;
        Long memberId = 1L;
        ProjectMemberCreateRequest request = new ProjectMemberCreateRequest(projectId, memberId);

        // when
        ResultActions perform = mockMvc.perform(post("/api/v1/projects/{projectId}/projectmembers", projectId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)));

        // then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(member.getName()))
                .andExpect(jsonPath("$.email").value(member.getEmail()));

        perform.andDo(print())
                .andDo(document("projectmember-create",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        requestFields(
                                fieldWithPath("projectId").type(NUMBER).description("프로젝트 키"),
                                fieldWithPath("memberId").type(NUMBER).description("회원 키")),
                        responseFields(
                                fieldWithPath("name").type(STRING).description("이름"),
                                fieldWithPath("email").type(STRING).description("이메일"))));
    }

    @Test
    @DisplayName("프로젝트-회원 관계를 삭제한다")
    void projectMemberDelete() throws Exception {
        ResultActions perform = mockMvc.perform(delete("/api/v1/projects/{projectId}/projectmembers/{memberId}",
                1L, 1L)
                .contentType(MediaType.APPLICATION_JSON));

        perform.andExpect(status().isNoContent());

        perform.andDo(print())
                .andDo(document("projectmember-delete",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("projectId").description("프로젝트 키"),
                                parameterWithName("memberId").description("멤버 키"))));
    }

    @Test
    @DisplayName("프로젝트에 등록된 회원 목록을 조회한다")
    void projectMemberList() throws Exception {
        // given
        ProjectMemberResponse response = new ProjectMemberResponse("name", "test@corookie.com");

        given(projectMemberService.findByProjectId(any(Long.class))).willReturn(List.of(response));

        // when
        ResultActions perform = mockMvc.perform(get("/api/v1/projects/{projectId}/projectmembers", 1L));

        // then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(response.name()))
                .andExpect(jsonPath("$[0].email").value(response.email()));

        perform.andDo(print())
                .andDo(document("projectmember-list",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("projectId").description("프로젝트 키")),
                        responseFields(
                                fieldWithPath("[].name").type(STRING).description("이름"),
                                fieldWithPath("[].email").type(STRING).description("이메일"))));
    }
}
