package com.fourttttty.corookie.plan.application.service;

import com.fourttttty.corookie.plan.application.repository.PlanCategoryRepository;
import com.fourttttty.corookie.plan.application.repository.PlanRepository;
import com.fourttttty.corookie.plan.domain.CategoryInPlan;
import com.fourttttty.corookie.plan.domain.Plan;
import com.fourttttty.corookie.plan.dto.request.PlanCreateRequest;
import com.fourttttty.corookie.plan.dto.request.PlanUpdateRequest;
import com.fourttttty.corookie.plan.dto.response.PlanCategoryResponse;
import com.fourttttty.corookie.plan.dto.response.PlanResponse;

import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlanService {

    private final PlanRepository planRepository;
    private final CategoryInPlanService categoryInPlanService;
    private final PlanCategoryService planCategoryService;
    private final ProjectRepository projectRepository;
    private final PlanCategoryRepository planCategoryRepository;

    public PlanResponse findById(Long id) {
        Plan plan = planRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if(!plan.getEnabled()) return null;
        return PlanResponse.from(plan, categoryInPlanService.findAllByPlan(plan).stream()
            .map(categoryInPlan -> PlanCategoryResponse.from(planCategoryService.findById(categoryInPlan.getId().getPlanCategory()
                .getId())))
            .toList());
    }

    @Transactional
    public PlanResponse createPlan(PlanCreateRequest planCreateRequest, Long projectId) {
        Plan plan = planRepository.save(Plan.of(planCreateRequest.planName(),
            planCreateRequest.description(),
            planCreateRequest.planStart(),
            planCreateRequest.planEnd(),
            true,
            projectRepository.findById(projectId).orElseThrow(EntityNotFoundException::new)));

        return PlanResponse.from(plan, planCreateRequest.categories().stream()
            .map(request -> categoryInPlanService.create(plan,request.content()))
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

        return PlanResponse.from(plan,categoryInPlanService.findAllByPlan(plan).stream()
            .map(categoryInPlan -> PlanCategoryResponse.from(categoryInPlan.getId().getPlanCategory()))
            .toList());
    }

    @Transactional
    public void deletePlan(Long planId) {
        planRepository.findById(planId).orElseThrow(EntityNotFoundException::new).delete();
    }

    @Transactional
    public PlanCategoryResponse createPlanCategory(Long id, String content){
        return categoryInPlanService.create(planRepository.findById(id).orElseThrow(EntityNotFoundException::new),content);
    }

    @Transactional
    public void deleteCategory(Long id, String content){
        categoryInPlanService.deleteCategoryInPlan(CategoryInPlan.of(planRepository.findById(id).orElseThrow(EntityNotFoundException::new),
            planCategoryRepository.findByContent(content).orElseThrow(EntityNotFoundException::new)));
    }
}
