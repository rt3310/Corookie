package com.fourttttty.corookie.project.presentation;

import com.fourttttty.corookie.issue.dto.request.IssueCreateRequest;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.project.application.service.ProjectService;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.project.dto.request.ProjectCreateRequest;
import com.fourttttty.corookie.project.dto.response.ProjectResponse;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.JsonFieldType.BOOLEAN;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProjectController.class)
public class ProjectControllerTest extends RestDocsTest {
    @MockBean
    private ProjectService projectService;

    private Member member;
    private Project project;

    @BeforeEach
    void initTexture() {
        member = new Member("member");
        project = Project.of("name",
                "description",
                true,
                "http://test.com",
                false,
                member
                //"2023-07-28",
                //"2023-08-03",
                );
    }

    @Test
    @DisplayName("프로젝트 생성")
    void createProject() throws Exception {
        //given
        given(projectService.create(any(ProjectCreateRequest.class), any(Long.class)))
                .willReturn(ProjectResponse.from(project));

        ProjectCreateRequest request = new ProjectCreateRequest("name", "description", "http://test.com", Boolean.FALSE);


        //when
        ResultActions perform = mockMvc.perform(post("/api/v1/projects?memberId=1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)));

        //then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(request.name()))
                .andExpect(jsonPath("$.description").value(request.description()))
                .andExpect(jsonPath("$.invitationLink").value(request.invitationLink()))
                .andExpect(jsonPath("$.invitationStatus").value(request.invitationStatus()));

        perform.andDo(print())
                .andDo(document("post-create",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        queryParameters(
                                parameterWithName("memberId").description("멤버 키")),
                        requestFields(
                                fieldWithPath("name").type(STRING).description("제목"),
                                fieldWithPath("description").type(STRING).description("내용"),
                                fieldWithPath("invitationLink").type(STRING).description("초대 링크"),
                                fieldWithPath("invitationStatus").type(BOOLEAN).description("초대 상태")),
                        responseFields(
                                fieldWithPath("name").type(STRING).description("제목"),
                                fieldWithPath("description").type(STRING).description("내용"),
                                fieldWithPath("enabled").type(BOOLEAN).description("활성화 여부"),
                                fieldWithPath("invitationLink").type(STRING).description("초대 링크"),
                                //fieldWithPath("createdAt").type(STRING).description("생성 날짜"),
                                //fieldWithPath("updatedAt").type(STRING).description("수정 날짜"),
                                fieldWithPath("invitationStatus").type(BOOLEAN).description("초대 상태") )));
    }

    @Test
    @DisplayName("프로젝트 수정")
    void modifyProject() throws Exception {
        //given

        //when

        //then
    }


}
