package com.fourttttty.corookie.plan.presentation;

import static com.fourttttty.corookie.support.ApiDocumentUtils.getDocumentRequest;
import static com.fourttttty.corookie.support.ApiDocumentUtils.getDocumentResponse;

import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.plan.application.service.PlanService;
import com.fourttttty.corookie.plan.domain.Plan;
import com.fourttttty.corookie.plan.domain.PlanCategory;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.JsonFieldType.ARRAY;
import static org.springframework.restdocs.payload.JsonFieldType.NUMBER;
import static org.springframework.restdocs.payload.JsonFieldType.STRING;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fourttttty.corookie.plan.dto.request.PlanCategoryCreateRequest;
import com.fourttttty.corookie.plan.dto.request.PlanCategoryUpdateRequest;
import com.fourttttty.corookie.plan.dto.request.PlanCreateRequest;
import com.fourttttty.corookie.plan.dto.request.PlanUpdateRequest;
import com.fourttttty.corookie.plan.dto.response.PlanCategoryResponse;
import com.fourttttty.corookie.plan.dto.response.PlanResponse;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.support.RestDocsTest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@WebMvcTest(PlanController.class)
class planControllerTest extends RestDocsTest {
    @MockBean
    private PlanService planService;

    private Member member;
    private Project project;
    private Plan plan;
    private List<PlanCategory> planCategories;

    private LocalDateTime start;
    private LocalDateTime end;
    private DateTimeFormatter dateTimeFormatter;


    @BeforeEach
    void initTexture(){
        member = new Member("name");
        project = Project.of("project",
            "description",
            true,
            "http://test.com",
            false,
            member);
        plan = Plan.of(
            1L,
            "planName",
            "planDescription",
            LocalDateTime.now().minusDays(2),
            LocalDateTime.now(),
            true,
            project
        );
        planCategories = new ArrayList<>(){
            {
                add(PlanCategory.of(1L,"testCategory1"));
                add(PlanCategory.of(2L,"testCategory2"));
                add(PlanCategory.of(3L,"testCategory3"));
            }
        };
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    }
    @Test
    @DisplayName("일정 생성")
    void planCreate() throws Exception{
        //given
        BDDMockito.given(planService.createPlan(any(PlanCreateRequest.class),any(Long.class)))
            .willReturn(PlanResponse.from(plan,planCategories.stream()
                    .map(PlanCategoryResponse::from)
                    .toList()));

        PlanCreateRequest request = new PlanCreateRequest(
            "planName",
            "planDescription",
            plan.getPlanStart(),
            plan.getPlanEnd(),
            planCategories.stream()
                .map(planCategory -> new PlanCategoryCreateRequest(planCategory.getContent()))
                .toList()
        );
        //when
        ResultActions perform = mockMvc.perform(post("/api/v1/projects/{projectId}/plans",1L)
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJson(request)));

        //then
        perform.andExpect(status().isOk())
            .andExpect(jsonPath("$.planName").value(request.planName()))
            .andExpect(jsonPath("$.description").value(request.description()))
            .andExpect(jsonPath("$.planStart").value(request.planStart().format(dateTimeFormatter)))
            .andExpect(jsonPath("$.planEnd").value(request.planEnd().format(dateTimeFormatter)))
            .andExpect(jsonPath("$.categories[0].content").value(request.categories().get(0).content()));

        perform.andDo(print())
            .andDo(document("post-create",
                getDocumentRequest(),
                getDocumentResponse(),
                pathParameters(
                    parameterWithName("projectId").description("프로젝트 키")),
                requestFields(
                    fieldWithPath("planName").type(STRING).description("일정 이름"),
                    fieldWithPath("description").type(STRING).description("일정 설명"),
                    fieldWithPath("planStart").type(STRING).description("일정 시작일"),
                    fieldWithPath("planEnd").type(STRING).description("일정 종료일"),
                    fieldWithPath("categories").type(ARRAY).description("일정 카테고리"),
                    fieldWithPath("categories.[].content").type(STRING).description("일정 카테고리 내용")),
                responseFields(
                    fieldWithPath("planName").type(STRING).description("일정 이름"),
                    fieldWithPath("description").type(STRING).description("일정 설명"),
                    fieldWithPath("planStart").type(STRING).description("일정 시작일"),
                    fieldWithPath("planEnd").type(STRING).description("일정 종료일"),
                    fieldWithPath("categories").type(ARRAY).description("일정 카테고리"),
                    fieldWithPath("categories.[].id").type(NUMBER).description("일정 카테고리 id"),
                    fieldWithPath("categories.[].content").type(STRING).description("일정 카테고리 내용"),
                    fieldWithPath("planId").type(NUMBER).description("일정 id"))));
    }

    @Test
    @DisplayName("일정 호출")
    void planDetail() throws Exception {
        //given
        PlanResponse planResponse = PlanResponse.from(plan,planCategories.stream()
            .map(PlanCategoryResponse::from)
            .toList());

        BDDMockito.given(planService.findById(any(Long.class)))
            .willReturn(planResponse);

        //when
        ResultActions perform = mockMvc.perform(get("/api/v1/projects/{projectId}/plans/{planId}",1L,1L));

        //then
        perform.andExpect(status().isOk())
            .andExpect(jsonPath("$.planId").value(planResponse.planId()))
            .andExpect(jsonPath("$.planName").value(planResponse.planName()))
            .andExpect(jsonPath("$.description").value(planResponse.description()))
            .andExpect(jsonPath("$.planStart").value(planResponse.planStart().format(dateTimeFormatter)))
            .andExpect(jsonPath("$.planEnd").value(planResponse.planEnd().format(dateTimeFormatter)))
            .andExpect(jsonPath("$.categories[0].content").value(planResponse.categories().get(0).content()));

        perform.andDo(print())
            .andDo(document("get-find",
                getDocumentResponse(),
                pathParameters(
                    parameterWithName("projectId").description("프로젝트 id"),
                    parameterWithName("planId").description("플랜 id")),
                responseFields(
                    fieldWithPath("planName").type(STRING).description("일정 이름"),
                    fieldWithPath("description").type(STRING).description("일정 설명"),
                    fieldWithPath("planStart").type(STRING).description("일정 시작일"),
                    fieldWithPath("planEnd").type(STRING).description("일정 종료일"),
                    fieldWithPath("categories").type(ARRAY).description("일정 카테고리"),
                    fieldWithPath("categories.[].id").type(NUMBER).description("일정 카테고리 id"),
                    fieldWithPath("categories.[].content").type(STRING).description("일정 카테고리 내용"),
                    fieldWithPath("planId").type(NUMBER).description("일정 id"))));
    }



    @Test
    @DisplayName("일정 수정")
    void planModify() throws Exception {
        //given
        PlanUpdateRequest request = new PlanUpdateRequest(
            "planNameUpdate",
            "planDescriptionUpdate",
            plan.getPlanStart().plusDays(2),
            plan.getPlanEnd().plusDays(2),
            planCategories.stream()
                .map(planCategory -> new PlanCategoryUpdateRequest(planCategory.getContent()))
                .toList()
        );

        BDDMockito.given(planService.modifyPlan(any(PlanUpdateRequest.class),any(Long.class),any(Long.class)))
            .willReturn(PlanResponse.from(Plan.of(1L,
                    request.planName(),
                    request.description(),
                    request.planStart(),
                    request.planEnd(),
                    true,
                    project),
                planCategories.stream()
                .map(PlanCategoryResponse::from)
                .toList()));

        //when
        ResultActions perform = mockMvc.perform(put("/api/v1/projects/{projectId}/plans/{planId}",1L,1L)
            .contentType(MediaType.APPLICATION_JSON)
            .content(toJson(request)));

        //then
        perform.andExpect(status().isOk())
            .andExpect(jsonPath("$.planName").value(request.planName()))
            .andExpect(jsonPath("$.description").value(request.description()))
            .andExpect(jsonPath("$.planStart").value(request.planStart().format(dateTimeFormatter)))
            .andExpect(jsonPath("$.planEnd").value(request.planEnd().format(dateTimeFormatter)));

        perform.andDo(print())
            .andDo(document("put-modify",
                getDocumentRequest(),
                getDocumentResponse(),
                pathParameters(
                    parameterWithName("projectId").description("프로젝트 키"),
                    parameterWithName("planId").description("일정 키")),
                requestFields(
                    fieldWithPath("planName").type(STRING).description("일정 이름"),
                    fieldWithPath("description").type(STRING).description("일정 설명"),
                    fieldWithPath("planStart").type(STRING).description("일정 시작일"),
                    fieldWithPath("planEnd").type(STRING).description("일정 종료일"),
                    fieldWithPath("categories").type(ARRAY).description("일정 카테고리"),
                    fieldWithPath("categories.[].content").type(STRING).description("일정 카테고리 내용")),
                responseFields(
                    fieldWithPath("planName").type(STRING).description("일정 이름"),
                    fieldWithPath("description").type(STRING).description("일정 설명"),
                    fieldWithPath("planStart").type(STRING).description("일정 시작일"),
                    fieldWithPath("planEnd").type(STRING).description("일정 종료일"),
                    fieldWithPath("categories").type(ARRAY).description("일정 카테고리"),
                    fieldWithPath("categories.[].id").type(NUMBER).description("일정 카테고리 id"),
                    fieldWithPath("categories.[].content").type(STRING).description("일정 카테고리 내용"),
                    fieldWithPath("planId").type(NUMBER).description("일정 id"))));
    }

    @Test
    @DisplayName("일정 삭제")
    void planDelete() throws Exception {
        //given

        //when
        ResultActions perform = mockMvc.perform(delete("/api/v1/projects/{projectId}/plans/{planId}",1L,1L));

        //then
        perform.andExpect(status().isNoContent());

        perform.andDo(print())
            .andDo(document("delete-plan",
                pathParameters(
                    parameterWithName("projectId").description("프로젝트 키"),
                    parameterWithName("planId").description("일정 키"))));

    }

    @Test
    @DisplayName("카테고리 추가")
    void categoryCreate() throws Exception {
        //given
        PlanCategoryResponse response = PlanCategoryResponse.from(planCategories.get(0));

        BDDMockito.given(planService.createPlanCategory(any(Long.class),any(String.class)))
            .willReturn(response);

        //when
        ResultActions perform = mockMvc.perform(post("/api/v1/projects/{projectId}/plans/{planId}/categories?categoryContent=testCategory1",1L,1L));

        //then
        perform.andExpect(status().isOk())
            .andExpect(jsonPath("$.content").value(response.content()))
            .andExpect(jsonPath("$.id").value(response.id()));

        perform.andDo(print())
            .andDo(document("post-categoryCreate",
                getDocumentResponse(),
                pathParameters(
                    parameterWithName("projectId").description("프로젝트 키"),
                    parameterWithName("planId").description("일정 키")),
                queryParameters(
                    parameterWithName("categoryContent").description("일정 카테고리 내용")),
                responseFields(
                    fieldWithPath("content").type(STRING).description("카테고리 내용"),
                    fieldWithPath("id").type(NUMBER).description("카테고리 id"))));
    }
    @Test
    @DisplayName("카테고리 삭제")
    void categoryDelete() throws Exception {
        //given

        //when
        ResultActions perform = mockMvc.perform(delete("/api/v1/projects/{projectId}/plans/{planId}/categories?categoryContent=testCategory1",1L,1L));

        //then
        perform.andExpect(status().isNoContent());

        perform.andDo(print())
            .andDo(document("delete-categoryDelete",
                pathParameters(
                    parameterWithName("projectId").description("프로젝트 키"),
                    parameterWithName("planId").description("일정 키")),
                queryParameters(
                    parameterWithName("categoryContent").description("일정 카테고리 내용"))));
    }


}