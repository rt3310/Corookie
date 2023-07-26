package com.fourttttty.corookie.plan.application.service;

import com.fourttttty.corookie.plan.application.repository.PlanCategoryRepository;
import com.fourttttty.corookie.plan.domain.PlanCategory;
import com.fourttttty.corookie.plan.domain.Plan;
import com.fourttttty.corookie.plan.dto.request.PlanCategoryCreateRequest;
import com.fourttttty.corookie.plan.dto.response.PlanCategoryResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlanCategoryService {

    private final PlanCategoryRepository planCategoryRepository;

    @Transactional
    public PlanCategoryResponse create(PlanCategory planCategory) {
        return new PlanCategoryResponse(planCategoryRepository.save(planCategory).getContent());
    }

    public Boolean findByContentAndPlan(String content, Plan plan) {
        return planCategoryRepository.findByContentAndPlan(content, plan).isEmpty();
    }

    public List<PlanCategoryResponse> findPlanCategoriesByPlan(Plan plan) {
        return planCategoryRepository.findAllByPlan(plan).stream()
            .map(planCategory -> new PlanCategoryResponse(planCategory.getContent()))
            .toList();
    }

    @Transactional
    public List<PlanCategoryResponse> createCategoriesByPlan(Plan plan, List<PlanCategoryCreateRequest> planCategories) {
        return planCategoryRepository.savePlanCategories(planCategories.stream()
                .distinct()
                .map(planCategory->planCategory.toEntity(plan))
                .toList()).stream()
            .map(planCategory -> new PlanCategoryResponse(planCategory.getContent()))
            .toList();
    }
}
