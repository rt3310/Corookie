package com.fourttttty.corookie.plan.application.service;

import com.fourttttty.corookie.plan.application.repository.CategoryInPlanRepository;
import com.fourttttty.corookie.plan.application.repository.PlanCategoryRepository;
import com.fourttttty.corookie.plan.domain.CategoryInPlan;
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
    private final CategoryInPlanRepository categoryInPlanRepository;

    @Transactional
    public PlanCategoryResponse create(Plan plan,PlanCategoryCreateRequest planCategoryCreateRequest) {
        PlanCategory newPlanCategory = planCategoryRepository.findByContent(planCategoryCreateRequest.content())
            .orElse(planCategoryRepository.save(planCategoryCreateRequest.toEntity()));

        categoryInPlanRepository.save(new CategoryInPlan(plan,newPlanCategory));

        return new PlanCategoryResponse(newPlanCategory.getContent());
    }

    public List<PlanCategoryResponse> findAllByPlan(Plan plan){
        List<CategoryInPlan> categoryInPlans = categoryInPlanRepository.findAllbyPlan(plan);
        return categoryInPlans.stream()
            .map(categoryInPlan -> new PlanCategoryResponse(categoryInPlan.getId().getPlanCategory().getContent()))
            .toList();
    }
}
