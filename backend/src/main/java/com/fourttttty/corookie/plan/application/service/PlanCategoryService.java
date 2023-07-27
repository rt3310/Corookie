package com.fourttttty.corookie.plan.application.service;

import com.fourttttty.corookie.plan.application.repository.PlanCategoryRepository;
import com.fourttttty.corookie.plan.domain.PlanCategory;
import com.fourttttty.corookie.plan.domain.Plan;
import com.fourttttty.corookie.plan.dto.response.PlanCategoryResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlanCategoryService {
    private final PlanCategoryRepository planCategoryRepository;
    private final CategoryInPlanService categoryInPlanService;

    @Transactional
    public PlanCategoryResponse create(Plan plan, String content) {
        PlanCategory newPlanCategory = planCategoryRepository.findByContent(content)
            .orElse(planCategoryRepository.save(PlanCategory.of(content)));
        categoryInPlanService.create(plan, newPlanCategory);

        return new PlanCategoryResponse(newPlanCategory.getId(), newPlanCategory.getContent());
    }

    public List<PlanCategoryResponse> findAllByPlan(Plan plan) {
        return categoryInPlanService.findAllByPlan(plan).stream()
            .map(categoryInPlan -> PlanCategoryResponse.from(
                categoryInPlan.getId().getPlanCategory().getId(),
                categoryInPlan.getId().getPlanCategory().getContent()))
            .toList();
    }

    public PlanCategoryResponse findByContent(String content) {
        PlanCategory planCategory = planCategoryRepository.findByContent(content)
            .orElse(planCategoryRepository.save(PlanCategory.of(content)));
        return PlanCategoryResponse.from(planCategory.getId(), planCategory.getContent());
    }
}
