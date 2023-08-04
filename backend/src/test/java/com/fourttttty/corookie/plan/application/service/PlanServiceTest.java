package com.fourttttty.corookie.plan.application.service;

import com.fourttttty.corookie.member.application.repository.MemberRepository;
import com.fourttttty.corookie.member.domain.AuthProvider;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.member.domain.Oauth2;
import com.fourttttty.corookie.plan.application.repository.CategoryInPlanRepository;
import com.fourttttty.corookie.plan.application.repository.PlanCategoryRepository;
import com.fourttttty.corookie.plan.application.repository.PlanMemberRepository;
import com.fourttttty.corookie.plan.application.repository.PlanRepository;
import com.fourttttty.corookie.plan.domain.Plan;
import com.fourttttty.corookie.plan.dto.request.PlanCategoryCreateRequest;
import com.fourttttty.corookie.plan.dto.request.PlanCategoryDeleteRequest;
import com.fourttttty.corookie.plan.dto.request.PlanCreateRequest;
import com.fourttttty.corookie.plan.dto.request.PlanMemberCreateRequest;
import com.fourttttty.corookie.plan.dto.request.PlanMemberDeleteRequest;
import com.fourttttty.corookie.plan.dto.request.PlanUpdateRequest;
import com.fourttttty.corookie.plan.dto.response.PlanResponse;
import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.texture.member.application.repository.FakeMemberRepository;
import com.fourttttty.corookie.texture.plan.application.repository.FakeCategoryInPlanRepository;
import com.fourttttty.corookie.texture.plan.application.repository.FakePlanCategoryRepository;
import com.fourttttty.corookie.texture.plan.application.repository.FakePlanMemberRepository;
import com.fourttttty.corookie.texture.plan.application.repository.FakePlanRepository;
import com.fourttttty.corookie.texture.project.application.repository.FakeProjectRepository;
import java.time.LocalDateTime;
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
    PlanMemberRepository planMemberRepository;

    PlanService planService;
    PlanCategoryService planCategoryService;
    CategoryInPlanService categoryInPlanService;
    PlanMemberService planMemberService;

    private Member member;
    private Project project;
    private PlanCreateRequest planCreateRequest;
    private Plan plan;


    @BeforeEach
    void initObjects() {
        planRepository = new FakePlanRepository();
        categoryInPlanRepository = new FakeCategoryInPlanRepository(planRepository);
        planCategoryRepository = new FakePlanCategoryRepository(categoryInPlanRepository);
        memberRepository = new FakeMemberRepository();
        projectRepository = new FakeProjectRepository();
        planMemberRepository = new FakePlanMemberRepository(planRepository);

        planCategoryService = new PlanCategoryService(planCategoryRepository);
        categoryInPlanService = new CategoryInPlanService(categoryInPlanRepository,planRepository,planCategoryService);
        planMemberService = new PlanMemberService(planMemberRepository,memberRepository,planRepository);
        planService = new PlanService(planRepository,categoryInPlanService,projectRepository,planCategoryRepository,planMemberService,memberRepository);

        member = Member.of( "name", "test@gmail.com", Oauth2.of(AuthProvider.KAKAO, "account"));
        project = Project.of("name", "description", true,
            "http://test.com", false, member);

        plan = Plan.of("name",
            "testDescription",
            LocalDateTime.now(),
            LocalDateTime.now().minusDays(2),
            true,
            project);

        planCreateRequest = new PlanCreateRequest("createPlan",
            "createPlanDescription",
            LocalDateTime.now().minusDays(2),
            LocalDateTime.now(),
            List.of(new PlanCategoryCreateRequest("CreateCategory")),
            List.of(new PlanMemberCreateRequest(1L)));
    }

    @Test
    @DisplayName("일정 생성")
    void createPlan() {
        // given

        // when
        memberRepository.save(member);
        projectRepository.save(project);
        PlanResponse response = planService.createPlan(planCreateRequest, 1L);

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
        PlanResponse response = planService.createPlan(planCreateRequest, 1L);

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
        PlanResponse savedResponse = planService.createPlan(planCreateRequest, 1L);

        PlanUpdateRequest updateRequest = new PlanUpdateRequest("modifyPlan",
            "modifyPlanDescription",
            LocalDateTime.now().minusDays(4),
            LocalDateTime.now().minusDays(2),
            List.of(new PlanCategoryDeleteRequest("modifyCategory1")),
            List.of(new PlanMemberDeleteRequest(1L)));

        // when
        PlanResponse modifiedResponse = planService.modifyPlan(updateRequest, 1L, 1L);

        // then
        assertThat(modifiedResponse.planName()).isEqualTo(updateRequest.planName());
        assertThat(modifiedResponse.description()).isEqualTo(updateRequest.description());
    }

    @Test
    @DisplayName("일정 삭제")
    void deletePlan() {
        // given
        memberRepository.save(member);
        projectRepository.save(project);
        planService.createPlan(planCreateRequest, 1L);

        //when
        planService.deletePlan(1L);

        //then
        assertThat(planService.findById(1L).enabled()).isEqualTo(false);
    }
}