package com.fourttttty.corookie.project.presentation;

import com.fourttttty.corookie.member.domain.AuthProvider;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.member.domain.Oauth2;
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

import static com.fourttttty.corookie.support.ApiDocumentUtils.getDocumentRequest;
import static com.fourttttty.corookie.support.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

        ProjectMemberCreateRequest request = new ProjectMemberCreateRequest(1L, 1L);
        ProjectMemberResponse response = ProjectMemberResponse.from(ProjectMember.of(project, member));

        // when
        ResultActions perform = mockMvc.perform(post("/api/v1/projects/{projectId}/projectmembers", projectId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)));

        // then
        perform.andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(.getName()))
                .andExpect(jsonPath("$.email").value(member.getEmail()));

        perform.andDo(print())
                .andDo(document("projectmember-create",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("projectId").description("프로젝트 키")),
                        requestFields(
                                fieldWithPath("name").type(STRING).description("이름"),
                                fieldWithPath("email").type(STRING).description("이메일")),
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
    @DisplayName("프로젝트에 참여 중인 회원 수를 구한다")
    void projectMemberCount() throws Exception{

    }

    void projectMemberList() {

    }
}
