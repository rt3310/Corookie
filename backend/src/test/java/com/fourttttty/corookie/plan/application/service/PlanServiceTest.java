package com.fourttttty.corookie.plan.application.service;

import static org.junit.jupiter.api.Assertions.*;

import com.fourttttty.corookie.member.application.repository.MemberRepository;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.plan.application.repository.CategoryInPlanRepository;
import com.fourttttty.corookie.plan.application.repository.PlanCategoryRepository;
import com.fourttttty.corookie.plan.application.repository.PlanRepository;
import com.fourttttty.corookie.plan.dto.request.PlanCategoryCreateRequest;
import com.fourttttty.corookie.plan.dto.request.PlanCreateRequest;
import com.fourttttty.corookie.plan.dto.response.PlanCategoryResponse;
import com.fourttttty.corookie.plan.dto.response.PlanResponse;
import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.texture.member.application.repository.FakeMemberRepository;
import com.fourttttty.corookie.texture.plan.application.repository.FakeCategoryInPlanRepository;
import com.fourttttty.corookie.texture.plan.application.repository.FakePlanCategoryRepository;
import com.fourttttty.corookie.texture.plan.application.repository.FakePlanRepository;
import com.fourttttty.corookie.texture.project.application.repository.FakeProjectRepository;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
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


    @BeforeEach
    void initObjects(){
        planRepository = new FakePlanRepository();
        planCategoryRepository = new FakePlanCategoryRepository();
        categoryInPlanRepository = new FakeCategoryInPlanRepository();
        memberRepository = new FakeMemberRepository();
        projectRepository = new FakeProjectRepository();

        categoryInPlanService = new CategoryInPlanService(categoryInPlanRepository,planCategoryService);
        planCategoryService = new PlanCategoryService(planCategoryRepository,categoryInPlanService);
        planService = new PlanService(planRepository,planCategoryService,categoryInPlanService,projectRepository);

        member = new Member("name");
        project = Project.of("name", "description", true,
            "http://test.com", false, member);
    }

    @Test
    @DisplayName("Make Plan")
    void createPlan() {
        // given
        PlanCreateRequest request = new PlanCreateRequest("testPlan",
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

        // when
        memberRepository.save(member);
        projectRepository.save(project);
        PlanResponse response = planService.createPlan(request,1L);

        // then
        assertThat(response.planName()).isEqualTo(request.planName());
        assertThat(response.description()).isEqualTo(request.description());
        assertThat(response.planStart()).isEqualTo(request.planStart());
        assertThat(response.planEnd()).isEqualTo(request.planEnd());
        System.out.println(response.toString());
        for (PlanCategoryResponse planCategoryResponse : response.categories()
        ) {
            System.out.println(planCategoryResponse.toString());
        }
    }

    @Test
    void findById() {
    }

    @Test
    void modifyPlan() {
    }

    @Test
    void deletePlan() {
    }
}