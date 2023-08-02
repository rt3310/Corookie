package com.fourttttty.corookie.plan.application.service;

import com.fourttttty.corookie.plan.application.repository.CategoryInPlanRepository;
import com.fourttttty.corookie.plan.domain.CategoryInPlan;
import com.fourttttty.corookie.plan.domain.Plan;
import com.fourttttty.corookie.plan.domain.PlanCategory;
import com.fourttttty.corookie.plan.dto.request.PlanCategoryCreateRequest;
import com.fourttttty.corookie.plan.dto.response.PlanCategoryResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryInPlanService {

    private final CategoryInPlanRepository categoryInPlanRepository;
    private final PlanCategoryService planCategoryService;

    @Transactional
    public PlanCategoryResponse create(Plan plan, PlanCategoryCreateRequest request) {
        PlanCategory newPlanCategory = planCategoryService.create(request);
        categoryInPlanRepository.save(CategoryInPlan.of(plan,newPlanCategory));
        return PlanCategoryResponse.from(newPlanCategory);
    }

    public List<CategoryInPlan> findAllByPlan(Plan plan) {
        return categoryInPlanRepository.findAllbyPlan(plan);
    }

    @Transactional
    public void deleteCategoryInPlan(CategoryInPlan categoryInPlan) {
        categoryInPlanRepository.delete(categoryInPlan);
    }
}
