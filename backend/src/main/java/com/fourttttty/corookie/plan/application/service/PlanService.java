package com.fourttttty.corookie.plan.application.service;

import com.fourttttty.corookie.global.exception.PlanNotFoundException;
import com.fourttttty.corookie.global.exception.ProjectNotFoundException;
import com.fourttttty.corookie.member.application.repository.MemberRepository;
import com.fourttttty.corookie.plan.application.repository.PlanCategoryRepository;
import com.fourttttty.corookie.plan.application.repository.PlanRepository;
import com.fourttttty.corookie.plan.domain.Plan;
import com.fourttttty.corookie.plan.domain.PlanMember;
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
                    planCategoryRepository.findByContent(categoryInPlan.content())
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
                        .map(categoryRequest -> categoryInPlanService.create(plan.getId(), categoryRequest))
                        .toList(),
                request.members().stream()
                        .map(memberRequest -> planMemberService.create(plan, memberRequest))
                        .toList());
    }

    @Transactional
    public PlanResponse modifyPlan(PlanUpdateRequest request, Long planId) {
        Plan plan = findEntityById(planId);
        plan.update(request.planName(),
                request.description(),
                request.planStart(),
                request.planEnd());

        return PlanResponse.from(plan,
                categoryInPlanService.findAllByPlanId(planId),
                planMemberService.findAllByPlanId(planId));
    }

    @Transactional
    public void deletePlan(Long planId) {
        findEntityById(planId).delete();
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
