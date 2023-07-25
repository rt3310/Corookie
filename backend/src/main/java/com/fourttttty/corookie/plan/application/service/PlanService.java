package com.fourttttty.corookie.plan.application.service;

import com.fourttttty.corookie.plan.application.repository.PlanRepository;
import com.fourttttty.corookie.plan.domain.Plan;
import com.fourttttty.corookie.plan.dto.request.PlanCreateRequest;
import com.fourttttty.corookie.plan.dto.request.PlanUpdateRequest;
import com.fourttttty.corookie.plan.dto.response.CategoryResponse;
import com.fourttttty.corookie.plan.dto.response.PlanResponse;

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
    private final CategoryService categoryService;

    public PlanResponse findById(Long id) {
        Plan plan = planRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        List<CategoryResponse> categories = categoryService.findCategoriesByPlan(plan);
        return PlanResponse.of(plan, categories);
    }

    @Transactional
    public PlanResponse createPlan(PlanCreateRequest planCreateRequest) {
        Plan plan = Plan.builder()
            .planName(planCreateRequest.planName())
            .description(planCreateRequest.description())
            .planStart(planCreateRequest.planStart())
            .planEnd(planCreateRequest.planEnd())
            .build();
        planRepository.save(plan);
        List<CategoryResponse> categoriesResponse = categoryService.createCategoriesByPlan(plan, planCreateRequest.categories());

        return PlanResponse.of(plan, categoriesResponse);
    }

    @Transactional
    public void modifyPlan(Long planId, PlanUpdateRequest planUpdateRequest) {
        Plan plan = planRepository.findById(planId).orElseThrow(EntityNotFoundException::new);
        plan.update(planUpdateRequest.planName(), planUpdateRequest.description(),
            planUpdateRequest.planStart(), planUpdateRequest.planEnd());

        planUpdateRequest.categories().stream()
            .filter(category -> categoryService.findByCategoryContentAndPlan(category.categoryContent(),plan))
            .forEach(category -> categoryService.create(category.toEntity(plan)));
    }

    @Transactional
    public void deletePlan(Long planId) {
        planRepository.findById(planId).orElseThrow(EntityNotFoundException::new).delete();
    }
}
