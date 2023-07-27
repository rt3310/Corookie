package com.fourttttty.corookie.plan.application.service;

import com.fourttttty.corookie.plan.application.repository.PlanRepository;
import com.fourttttty.corookie.plan.domain.Plan;
import com.fourttttty.corookie.plan.dto.request.PlanCreateRequest;
import com.fourttttty.corookie.plan.dto.request.PlanUpdateRequest;
import com.fourttttty.corookie.plan.dto.response.PlanResponse;

import com.fourttttty.corookie.project.application.service.ProjectService;
import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlanService {

    private final PlanRepository planRepository;
    private final PlanCategoryService planCategoryService;
    private final ProjectService projectService;

    public PlanResponse findById(Long id) {
        Plan plan = planRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return PlanResponse.from(plan, planCategoryService.findAllByPlan(plan));
    }

    @Transactional
    public PlanResponse createPlan(PlanCreateRequest planCreateRequest, Long projectId) {
        Plan plan = planRepository.save(
                Plan.of(planCreateRequest.planName(),
                        planCreateRequest.description(),
                        planCreateRequest.planStart(),
                        planCreateRequest.planEnd(),
                        true,
                        projectService.findEntityById(projectId)));

        return PlanResponse.from(plan, planCreateRequest.categories().stream()
                .map(planCategoryCreateRequest -> planCategoryService.create(plan ,planCategoryCreateRequest))
                .toList());
    }

    @Transactional
    public void modifyPlan(PlanUpdateRequest planUpdateRequest, Long planId, Long projectId) {
        planRepository.findById(planId).orElseThrow(EntityNotFoundException::new)
                .update(planUpdateRequest.planName(),
                        planUpdateRequest.description(),
                        planUpdateRequest.planStart(),
                        planUpdateRequest.planEnd(),
                        projectService.findEntityById(projectId));
    }

    @Transactional
    public void deletePlan(Long planId) {
        planRepository.findById(planId).orElseThrow(EntityNotFoundException::new).delete();
    }
}
