package com.fourttttty.corookie.project.presentation;

import com.fourttttty.corookie.config.audit.JpaAuditingConfig;
import com.fourttttty.corookie.member.domain.AuthProvider;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.member.domain.Oauth2;
import com.fourttttty.corookie.project.application.service.ProjectService;
import com.fourttttty.corookie.project.dto.request.ProjectUpdateRequest;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.project.dto.request.ProjectCreateRequest;
import com.fourttttty.corookie.project.dto.response.ProjectResponse;
import com.fourttttty.corookie.support.RestDocsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.fourttttty.corookie.support.ApiDocumentUtils.getDocumentRequest;
import static com.fourttttty.corookie.support.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.JsonFieldType.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/*
To-Do :
JpaAuditingConfig 정상 동작하게 하기
'JPA metamodel must not be empty' 안 뜨게 하기
createdAt, updatedAt 자동 생성되게 하기
 */
@Import(JpaAuditingConfig.class)
@WebMvcTest(value = ProjectController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class ProjectControllerTest extends RestDocsTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    private Member member;
    private Project project;

    @Autowired
    private ApplicationContext applicationContext;


    @BeforeEach
    void initTexture() {
        member = Member.of("name", "test@gmail.com", Oauth2.of(AuthProvider.KAKAO, "account"));
        project = Project.of("name",
                "description",
                true,
                "http://test.com",
                false,
                member
                );
    }

    @Test
    @DisplayName("프로젝트 목록 조회")
    void projectList() throws Exception {
        // given
        List<ProjectResponse> projects = new ArrayList<>();

        ProjectResponse project1 = new ProjectResponse("Project1", "Description1", LocalDateTime.now(), LocalDateTime.now(), false, "http://test1.com", false);
        ProjectResponse project2 = new ProjectResponse("Project2", "Description2", LocalDateTime.now(), LocalDateTime.now(), true, "http://test1.com", false);
        projects.add(project1);
        projects.add(project2);

        given(projectService.findAll()).willReturn(projects);

        //when
        ResultActions perform = mockMvc.perform(get("/api/v1/projects"));

        // then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Project1"))
                .andExpect(jsonPath("$[0].description").value("Description1"))
                .andExpect(jsonPath("$[0].invitationLink").value("http://test1.com"))
                .andExpect(jsonPath("$[0].invitationStatus").value(false))
                .andExpect(jsonPath("$[1].name").value("Project2"))
                .andExpect(jsonPath("$[1].description").value("Description2"))
                .andExpect(jsonPath("$[1].invitationLink").value("http://test2.com"))
                .andExpect(jsonPath("$[1].invitationStatus").value(true));
    }

    @Test
    @DisplayName("프로젝트 상세 조회")
    void projectDetail() throws Exception {
        // given
        ProjectResponse project = new ProjectResponse("Project1", "Description1", LocalDateTime.now(), LocalDateTime.now(), false,"http://test1.com", false);
        given(projectService.findById(1L)).willReturn(project);

        //when
        ResultActions perform = mockMvc.perform(get("/api/v1/projects/1"));

        //then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Project1"))
                .andExpect(jsonPath("$.description").value("Description1"))
                .andExpect(jsonPath("$.invitationLink").value("http://test1.com"))
                .andExpect(jsonPath("$.invitationStatus").value(false));
    }


    @Test
    @DisplayName("프로젝트 생성")
    void createProject() throws Exception {

        //given
        given(projectService.create(any(ProjectCreateRequest.class), any(Long.class)))
                .willReturn(ProjectResponse.from(project));

        ProjectCreateRequest request = new ProjectCreateRequest("name", "description", Boolean.FALSE);


        //when
        ResultActions perform = mockMvc.perform(post("/api/v1/projects?memberId=1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)));

        //then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(request.name()))
                .andExpect(jsonPath("$.description").value(request.description()))
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
                                fieldWithPath("createdAt").type(STRING).description("생성 날짜"),
                                fieldWithPath("updatedAt").type(STRING).description("수정 날짜"),
                                fieldWithPath("invitationStatus").type(BOOLEAN).description("초대 상태") )));
    }

    @Test
    @DisplayName("프로젝트 수정")
    void projectModify() throws Exception {
        // given
        ProjectUpdateRequest request = new ProjectUpdateRequest("Updated Project", "Updated Description", "http://updated.com", true);
        given(projectService.modify(any(ProjectUpdateRequest.class), any(Long.class))).willReturn(ProjectResponse.from(project));

        // when
        ResultActions perform = mockMvc.perform(put("/api/v1/projects/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)));

        // then
        perform.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Project"))
                .andExpect(jsonPath("$.description").value("Updated Description"))
                .andExpect(jsonPath("$.invitationLink").value("http://updated.com"))
                .andExpect(jsonPath("$.invitationStatus").value(true));

        perform.andDo(print())
                .andDo(document("put-update",
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
                                fieldWithPath("createdAt").type(STRING).description("생성 날짜"),
                                fieldWithPath("updatedAt").type(STRING).description("수정 날짜"),
                                fieldWithPath("invitationStatus").type(BOOLEAN).description("초대 상태") )));
    }

    @Test
    @DisplayName("프로젝트 삭제")
    void projectDelete() throws Exception {
        mockMvc.perform(delete("/api/v1/projects/1"))
                .andExpect(status().isNoContent());
    }
}
