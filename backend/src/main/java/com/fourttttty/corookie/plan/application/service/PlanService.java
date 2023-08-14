package com.fourttttty.corookie.plan.application.service;

import com.fourttttty.corookie.global.exception.PlanNotFoundException;
import com.fourttttty.corookie.global.exception.ProjectNotFoundException;
import com.fourttttty.corookie.member.application.repository.MemberRepository;
import com.fourttttty.corookie.plan.application.repository.PlanCategoryRepository;
import com.fourttttty.corookie.plan.application.repository.PlanRepository;
import com.fourttttty.corookie.plan.domain.CategoryInPlan;
import com.fourttttty.corookie.plan.domain.Plan;
import com.fourttttty.corookie.plan.domain.PlanMember;
import com.fourttttty.corookie.plan.dto.request.PlanCategoryCreateRequest;
import com.fourttttty.corookie.plan.dto.request.PlanCreateRequest;
import com.fourttttty.corookie.plan.dto.request.PlanMemberCreateRequest;
import com.fourttttty.corookie.plan.dto.request.PlanMemberDeleteRequest;
import com.fourttttty.corookie.plan.dto.request.PlanUpdateRequest;
import com.fourttttty.corookie.plan.dto.response.CalendarPlanResponse;
import com.fourttttty.corookie.plan.dto.response.PlanCategoryResponse;
import com.fourttttty.corookie.plan.dto.response.PlanMemberResponse;
import com.fourttttty.corookie.plan.dto.response.PlanResponse;

import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlanService {

    private final PlanRepository planRepository;
    private final ProjectRepository projectRepository;
    private final PlanCategoryRepository planCategoryRepository;
    private final MemberRepository memberRepository;
    private final CategoryInPlanService categoryInPlanService;
    private final PlanMemberService planMemberService;

    public List<CalendarPlanResponse> findByDate(LocalDate date) {
        return planRepository.findByDate(date).stream()
                .map(CalendarPlanResponse::from)
                .toList();
    }

    public PlanResponse findById(Long id) {
        Plan plan = planRepository.findById(id).orElseThrow(PlanNotFoundException::new);
        return PlanResponse.from(plan,
            categoryInPlanService.findAllByPlanId(id).stream()
                .map(categoryInPlan -> PlanCategoryResponse.from(
                    planCategoryRepository.findByContent(categoryInPlan.getId().getPlanCategory().getContent())
                        .orElseThrow(EntityNotFoundException::new)))
                .toList(),
            planMemberService.findAllByPlanId(id));
    }

    @Transactional
    public PlanResponse createPlan(PlanCreateRequest request, Long projectId) {
        Plan plan = planRepository.save(Plan.of(request.planName(),
                request.description(),
                request.planStart(),
                request.planEnd(),
                true,
                projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new)));

        return PlanResponse.from(plan,
                request.categories().stream()
                        .map(categoryRequest -> categoryInPlanService.create(projectId, plan, categoryRequest))
                        .toList(),
                request.members().stream()
                        .map(memberRequest -> planMemberService.create(plan, memberRequest))
                        .toList());
    }

    @Transactional
    public PlanResponse modifyPlan(PlanUpdateRequest request, Long planId,
        Long projectId) {
        Plan plan = findEntityById(planId);
        plan.update(request.planName(),
                request.description(),
                request.planStart(),
                request.planEnd(),
                projectRepository.findById(projectId).orElseThrow(EntityNotFoundException::new));

        return PlanResponse.from(plan,
                categoryInPlanService.findAllByPlanId(planId).stream()
                        .map(categoryInPlan -> PlanCategoryResponse.from(
                                categoryInPlan.getId().getPlanCategory()))
                        .toList(),
                planMemberService.findAllByPlanId(planId));
    }

    @Transactional
    public void deletePlan(Long planId) {
        findEntityById(planId).delete();
    }

    @Transactional
    public PlanCategoryResponse createPlanCategory(Long projectId, Long planId, PlanCategoryCreateRequest request) {
        return categoryInPlanService.create(projectId, findEntityById(planId), request);
    }

    @Transactional
    public void deletePlanCategory(Long id, String content) {
        categoryInPlanService.deleteCategoryInPlan(CategoryInPlan.of(findEntityById(id), planCategoryRepository.findByContent(content)
                    .orElseThrow(EntityNotFoundException::new)));
    }

    @Transactional
    public PlanMemberResponse createPlanMember(Long id, PlanMemberCreateRequest request) {
        return planMemberService.create(findEntityById(id), request);
    }

    @Transactional
    public void deletePlanMember(Long id, PlanMemberDeleteRequest request) {
        planMemberService.deletePlanMember(PlanMember.of(
            memberRepository.findById(request.memberId()).orElseThrow(EntityNotFoundException::new),
            findEntityById(id)));
    }

    private Plan findEntityById(Long id) {
        return planRepository.findById(id).orElseThrow(PlanNotFoundException::new);
    }
}
