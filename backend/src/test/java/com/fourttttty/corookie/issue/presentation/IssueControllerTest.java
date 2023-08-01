package com.fourttttty.corookie.issue.presentation;

import com.fourttttty.corookie.issue.application.service.IssueService;
import com.fourttttty.corookie.issue.domain.*;
import com.fourttttty.corookie.issue.dto.request.IssueCategoryCreateRequest;
import com.fourttttty.corookie.issue.dto.request.IssueCreateRequest;
import com.fourttttty.corookie.issue.dto.response.IssueCategoryResponse;
import com.fourttttty.corookie.issue.dto.response.IssueDetailResponse;
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
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static com.fourttttty.corookie.support.ApiDocumentUtils.getDocumentRequest;
import static com.fourttttty.corookie.support.ApiDocumentUtils.getDocumentResponse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
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

    private Member member;
    private Project project;
    private Issue issue;
    private IssueCategory issueCategory;

    @BeforeEach
    void initTexture() {
        member = Member.of("name", Oauth2.of(AuthProvider.KAKAO, "account"));
        project = Project.of("project",
                "description",
                true,
                "http://test.com",
                false,
                member);
        issue = Issue.of("topic",
                "description",
                IssueProgress.TODO,
                IssuePriority.HIGH,
                true,
                project,
                member);
        issueCategory = IssueCategory.of(Category.BACKEND, issue);
    }

    @Test
    @DisplayName("이슈 생성")
    void createIssue() throws Exception {
        // given
        given(issueService.create(any(IssueCreateRequest.class), any(Long.class), any(Long.class)))
                .willReturn(IssueDetailResponse.from(issue, List.of(IssueCategoryResponse.from(Category.BACKEND)), 1L));

        IssueCreateRequest request = new IssueCreateRequest("topic",
                "description",
                IssueProgress.TODO,
                IssuePriority.HIGH,
                List.of(new IssueCategoryCreateRequest(Category.BACKEND)));

        // when
        ResultActions perform = mockMvc.perform(post("/api/v1/projects/{projectId}/issues?memberId=1", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)));

        // then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.topic").value(request.topic()))
                .andExpect(jsonPath("$.description").value(request.description()))
                .andExpect(jsonPath("$.progress").value(request.progress().getValue()))
                .andExpect(jsonPath("$.priority").value(request.priority().getValue()))
                .andExpect(jsonPath("$.issueCategories[0].category").value(request.issueCategories().get(0).category().getValue()));

        perform.andDo(print())
                .andDo(document("issue-create",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        queryParameters(
                                parameterWithName("memberId").description("멤버 키")),
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
                                fieldWithPath("issueCategories.[].category").type(STRING).description("이슈 카테고리 내용"),
                                fieldWithPath("memberId").type(NUMBER).description("멤버 키"))));
    }
}