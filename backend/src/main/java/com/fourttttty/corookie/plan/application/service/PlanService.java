package com.fourttttty.corookie.plan.application.service;

import com.fourttttty.corookie.plan.application.repository.PlanRepository;
import com.fourttttty.corookie.plan.domain.Plan;
import com.fourttttty.corookie.plan.dto.request.PlanCreateRequest;
import com.fourttttty.corookie.plan.dto.request.PlanUpdateRequest;
import com.fourttttty.corookie.plan.dto.response.PlanCategoryResponse;
import com.fourttttty.corookie.plan.dto.response.PlanResponse;

import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import com.fourttttty.corookie.project.application.service.ProjectService;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlanService {

    private final PlanRepository planRepository;
    private final PlanCategoryService planCategoryService;
    private final CategoryInPlanService categoryInPlanService;
    private final ProjectRepository projectRepository;

    public PlanResponse findById(Long id) {
        Plan plan = planRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        List<PlanCategoryResponse> planCategories = planCategoryService.findAllByPlan(plan);
        return PlanResponse.from(plan, planCategories);
    }

    @Transactional
    public PlanResponse createPlan(Long projectId, PlanCreateRequest planCreateRequest) {
        Plan plan = Plan.of(planCreateRequest.planName(),
            planCreateRequest.description(),
            planCreateRequest.planStart(),
            planCreateRequest.planEnd(),
            true,
            projectRepository.findById(projectId).orElseThrow(EntityNotFoundException::new));
        planRepository.save(plan);

        return PlanResponse.from(plan, planCreateRequest.categories().stream()
            .map(planCategoryCreateRequest ->
                planCategoryService.create(plan,planCategoryCreateRequest.content()))
            .toList());
    }

    @Transactional
    public PlanResponse modifyPlan(PlanUpdateRequest planUpdateRequest, Long planId, Long projectId) {
        Plan plan = planRepository.findById(planId).orElseThrow(EntityNotFoundException::new);
        plan.update(planUpdateRequest.planName(),
                planUpdateRequest.description(),
                planUpdateRequest.planStart(),
                planUpdateRequest.planEnd(),
                projectRepository.findById(projectId).orElseThrow(EntityNotFoundException::new));

        return PlanResponse.from(plan,
            categoryInPlanService.modifyCategoryInPlan(plan,planUpdateRequest.categories()));
    }

    @Transactional
    public void deletePlan(Long planId) {
        planRepository.findById(planId).orElseThrow(EntityNotFoundException::new).delete();
    }
}
