package com.fourttttty.corookie.plan.application.service;

import com.fourttttty.corookie.member.application.repository.MemberRepository;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.plan.application.repository.CategoryInPlanRepository;
import com.fourttttty.corookie.plan.application.repository.PlanCategoryRepository;
import com.fourttttty.corookie.plan.application.repository.PlanRepository;
import com.fourttttty.corookie.plan.dto.request.PlanCategoryCreateRequest;
import com.fourttttty.corookie.plan.dto.request.PlanCategoryUpdateRequest;
import com.fourttttty.corookie.plan.dto.request.PlanCreateRequest;
import com.fourttttty.corookie.plan.dto.request.PlanUpdateRequest;
import com.fourttttty.corookie.plan.dto.response.PlanResponse;
import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.texture.member.application.repository.FakeMemberRepository;
import com.fourttttty.corookie.texture.plan.application.repository.FakeCategoryInPlanRepository;
import com.fourttttty.corookie.texture.plan.application.repository.FakePlanCategoryRepository;
import com.fourttttty.corookie.texture.plan.application.repository.FakePlanRepository;
import com.fourttttty.corookie.texture.project.application.repository.FakeProjectRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlanServiceTest {
    PlanRepository planRepository;
    PlanCategoryRepository planCategoryRepository;
    CategoryInPlanRepository categoryInPlanRepository;
    MemberRepository memberRepository;
    ProjectRepository projectRepository;
    PlanService planService;
    PlanCategoryService planCategoryService;
    CategoryInPlanService categoryInPlanService;

    private Member member;
    private Project project;
    private PlanCreateRequest planCreateRequest;
    private List<String> deleteList;


    @BeforeEach
    void initObjects(){
        planRepository = new FakePlanRepository();
        planCategoryRepository = new FakePlanCategoryRepository();
        categoryInPlanRepository = new FakeCategoryInPlanRepository();
        memberRepository = new FakeMemberRepository();
        projectRepository = new FakeProjectRepository();

        planCategoryService = new PlanCategoryService(planCategoryRepository);
        categoryInPlanService = new CategoryInPlanService(categoryInPlanRepository,planCategoryService);
        planService = new PlanService(planRepository,categoryInPlanService,planCategoryService,projectRepository,planCategoryRepository);

        member = new Member("name");
        project = Project.of("name", "description", true,
            "http://test.com", false, member);

        planCreateRequest= new PlanCreateRequest("testPlan",
            "planDescription",
            LocalDateTime.now().minusDays(2),
            LocalDateTime.now(),
            new ArrayList<PlanCategoryCreateRequest>() {
                {
                    add(new PlanCategoryCreateRequest("testCategory1"));
                    add(new PlanCategoryCreateRequest("testCategory2"));
                    add(new PlanCategoryCreateRequest("testCategory3"));
                    add(new PlanCategoryCreateRequest("testCategory1"));
                }
            });

        deleteList = new ArrayList<String>(){
            {
                add("testCategory1");
                add("testCategory2");
            }
        };
    }

    @Test
    @DisplayName("일정 생성")
    void createPlan() {
        // given

        // when
        memberRepository.save(member);
        projectRepository.save(project);
        PlanResponse response = planService.createPlan(planCreateRequest,1L);

        // then
        assertThat(response.planName()).isEqualTo(planCreateRequest.planName());
        assertThat(response.description()).isEqualTo(planCreateRequest.description());
        assertThat(response.planStart()).isEqualTo(planCreateRequest.planStart());
        assertThat(response.planEnd()).isEqualTo(planCreateRequest.planEnd());
    }

    @Test
    @DisplayName("일정 조회")
    void findById() {
        // given
        memberRepository.save(member);
        projectRepository.save(project);
        PlanResponse response = planService.createPlan(planCreateRequest,1L);

        // when
        PlanResponse foundResponse = planService.findById(1L);

        // then
        assertThat(planCreateRequest.planName()).isEqualTo(foundResponse.planName());
        assertThat(planCreateRequest.description()).isEqualTo(foundResponse.description());
        assertThat(planCreateRequest.planStart()).isEqualTo(foundResponse.planStart());
        assertThat(planCreateRequest.planEnd()).isEqualTo(foundResponse.planEnd());
    }

    @Test
    @DisplayName("일정 수정")
    void modifyPlan() {
        // given
        memberRepository.save(member);
        projectRepository.save(project);
        PlanResponse savedResponse = planService.createPlan(planCreateRequest,1L);

        PlanUpdateRequest updateRequest = new PlanUpdateRequest("modifyPlan",
            "modifyPlanDescription",
            LocalDateTime.now().minusDays(2),
            LocalDateTime.now(),
            new ArrayList<PlanCategoryUpdateRequest>() {
                {
                    add(new PlanCategoryUpdateRequest("modifyCategory1"));
                    add(new PlanCategoryUpdateRequest("modifyCategory2"));
                    add(new PlanCategoryUpdateRequest("testCategory3"));
                }
            });

        // when
        planService.deleteCategory(1L,"testCategory1");
        planService.deleteCategory(1L,"testCategory2");
        planService.createPlanCategory(1L,"updateCategory1");
        planService.createPlanCategory(1L,"updateCategory2");
        PlanResponse modifiedResponse = planService.modifyPlan(updateRequest,1L,1L);

        // then
        assertThat(modifiedResponse.planName()).isEqualTo("modifyPlan");
        assertThat(modifiedResponse.description()).isEqualTo("modifyPlanDescription");
    }

    @Test
    @DisplayName("일정 삭제")
    void deletePlan() {
        // given
        memberRepository.save(member);
        projectRepository.save(project);
        PlanResponse savedResponse = planService.createPlan(planCreateRequest,1L);

        //when
        planService.deletePlan(1L);

        //then
        assertThat(planService.findById(1L)).isNull();
    }
}