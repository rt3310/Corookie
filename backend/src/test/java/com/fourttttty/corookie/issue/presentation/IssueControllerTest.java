package com.fourttttty.corookie.issue.presentation;

import com.fourttttty.corookie.issue.application.service.IssueService;
import com.fourttttty.corookie.issue.domain.*;
import com.fourttttty.corookie.issue.dto.request.IssueCategoryCreateRequest;
import com.fourttttty.corookie.issue.dto.request.IssueCreateRequest;
import com.fourttttty.corookie.issue.dto.response.IssueCategoryResponse;
import com.fourttttty.corookie.issue.dto.response.IssueDetailResponse;
import com.fourttttty.corookie.issue.dto.response.IssueListResponse;
import com.fourttttty.corookie.member.domain.AuthProvider;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.member.domain.Oauth2;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.support.RestDocsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static com.fourttttty.corookie.support.ApiDocumentUtils.getDocumentRequest;
import static com.fourttttty.corookie.support.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IssueController.class)
class IssueControllerTest extends RestDocsTest {
    @MockBean
    private IssueService issueService;

    private Issue issue;
    private Member member;

    @BeforeEach
    void initTexture() {
        member = Member.of("name", "test@gmail.com", Oauth2.of(AuthProvider.KAKAO, "account"));
        issue = Issue.of("topic",
                "description",
                IssueProgress.TODO,
                IssuePriority.HIGH,
                true,
                Project.of("project",
                        "description",
                        true,
                        "http://test.com",
                        false,
                        member),
                member);
    }

    @Test
    @DisplayName("이슈를 생성한다")
    void createIssue() throws Exception {
        // given
        given(issueService.create(any(IssueCreateRequest.class), any(Long.class), any(Long.class)))
                .willReturn(IssueDetailResponse.from(issue, List.of(IssueCategoryResponse.from(Category.BACKEND))));

        IssueCreateRequest request = new IssueCreateRequest("topic",
                "description",
                IssueProgress.TODO,
                IssuePriority.HIGH,
                List.of(new IssueCategoryCreateRequest(Category.BACKEND)));

        // when
        ResultActions perform = mockMvc.perform(post("/api/v1/projects/{projectId}/issues", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)));

        // then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.topic").value(request.topic()))
                .andExpect(jsonPath("$.description").value(request.description()))
                .andExpect(jsonPath("$.progress").value(request.progress().getValue()))
                .andExpect(jsonPath("$.priority").value(request.priority().getName()))
                .andExpect(jsonPath("$.issueCategories[0].category")
                        .value(request.issueCategories().get(0).category().getValue()));

        perform.andDo(print())
                .andDo(document("issue-create",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("projectId").description("프로젝트 키")),
                        requestFields(
                                fieldWithPath("topic").type(STRING).description("제목"),
                                fieldWithPath("description").type(STRING).description("설명"),
                                fieldWithPath("progress").type(STRING).description("이슈 진행도"),
                                fieldWithPath("priority").type(STRING).description("이슈 중요도"),
                                fieldWithPath("issueCategories").type(ARRAY).description("이슈 카테고리"),
                                fieldWithPath("issueCategories.[].category").type(STRING).description("이슈 카테고리 내용")),
                        responseFields(
                                fieldWithPath("topic").type(STRING).description("제목"),
                                fieldWithPath("description").type(STRING).description("설명"),
                                fieldWithPath("progress").type(STRING).description("이슈 진행도"),
                                fieldWithPath("priority").type(STRING).description("이슈 중요도"),
                                fieldWithPath("issueCategories").type(ARRAY).description("이슈 카테고리"),
                                fieldWithPath("issueCategories.[].category").type(STRING).description("이슈 카테고리 내용"))));
    }

    @Test
    @DisplayName("이슈 목록을 조회한다")
    void issueList() throws Exception {
        // given
        given(issueService.findByProjectId(any(Long.class)))
                .willReturn(List.of(
                        IssueListResponse.from(issue, List.of(IssueCategoryResponse.from(Category.BACKEND)))));

        IssueCreateRequest request = new IssueCreateRequest("topic",
                "description",
                IssueProgress.TODO,
                IssuePriority.HIGH,
                List.of(new IssueCategoryCreateRequest(Category.BACKEND)));

        // when
        ResultActions perform = mockMvc.perform(get("/api/v1/projects/{projectId}/issues", 1L)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].topic").value(request.topic()))
                .andExpect(jsonPath("$[0].progress").value(request.progress().getValue()))
                .andExpect(jsonPath("$[0].priority").value(request.priority().getName()))
                .andExpect(jsonPath("$[0].issueCategories[0].category")
                        .value(request.issueCategories().get(0).category().getValue()))
                .andExpect(jsonPath("$[0].memberName").value(member.getName()));

        perform.andDo(print())
                .andDo(document("issue-list",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("projectId").description("프로젝트 키")),
                        responseFields(
                                fieldWithPath("[].topic").type(STRING).description("제목"),
                                fieldWithPath("[].progress").type(STRING).description("이슈 진행도"),
                                fieldWithPath("[].priority").type(STRING).description("이슈 중요도"),
                                fieldWithPath("[].issueCategories").type(ARRAY).description("이슈 카테고리"),
                                fieldWithPath("[].issueCategories.[].category").type(STRING).description("이슈 카테고리 내용"),
                                fieldWithPath("[].memberName").type(STRING).description("멤버 이름"))));
    }

    @Test
    @DisplayName("이슈 상세 정보를 조회한다")
    void issueDetail() throws Exception {
        IssueDetailResponse response = IssueDetailResponse.from(issue, List.of(IssueCategoryResponse.from(Category.BACKEND)));
        given(issueService.findById(any(Long.class)))
                .willReturn(response);

        // when
        ResultActions perform = mockMvc.perform(get("/api/v1/projects/{projectId}/issues/{issueId}",
                1L, 1L)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.topic").value(response.topic()))
                .andExpect(jsonPath("$.description").value(response.description()))
                .andExpect(jsonPath("$.progress").value(response.progress()))
                .andExpect(jsonPath("$.priority").value(response.priority()))
                .andExpect(jsonPath("$.issueCategories[0].category").value(response.issueCategories().get(0).category()));

        perform.andDo(print())
                .andDo(document("issue-detail",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("projectId").description("프로젝트 키"),
                                parameterWithName("issueId").description("이슈 키")),
                        responseFields(
                                fieldWithPath("topic").type(STRING).description("제목"),
                                fieldWithPath("description").type(STRING).description("설명"),
                                fieldWithPath("progress").type(STRING).description("이슈 진행도"),
                                fieldWithPath("priority").type(STRING).description("이슈 중요도"),
                                fieldWithPath("issueCategories").type(ARRAY).description("이슈 카테고리"),
                                fieldWithPath("issueCategories.[].category").type(STRING).description("이슈 카테고리 내용"))));
    }

    @Test
    @DisplayName("이슈를 삭제한다")
    void issueDelete() throws Exception {
        ResultActions perform = mockMvc.perform(delete("/api/v1/projects/{projectId}/issues/{issueId}",
                1L, 1L)
                .contentType(MediaType.APPLICATION_JSON));

        perform.andExpect(status().isNoContent());

        perform.andDo(print())
                .andDo(document("issue-delete",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("projectId").description("프로젝트 키"),
                                parameterWithName("issueId").description("이슈 키"))));
    }
}