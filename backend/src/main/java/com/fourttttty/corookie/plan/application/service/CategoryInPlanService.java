package com.fourttttty.corookie.plan.application.service;

import com.fourttttty.corookie.plan.application.repository.CategoryInPlanRepository;
import com.fourttttty.corookie.plan.domain.CategoryInPlan;
import com.fourttttty.corookie.plan.domain.Plan;
import com.fourttttty.corookie.plan.domain.PlanCategory;
import com.fourttttty.corookie.plan.dto.request.PlanCategoryUpdateRequest;
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
    public CategoryInPlan create(Plan plan, PlanCategory planCategory) {
        return categoryInPlanRepository.save(CategoryInPlan.of(plan, planCategory));
    }

    public List<CategoryInPlan> findAllByPlan(Plan plan) {
        return categoryInPlanRepository.findAllbyPlan(plan);
    }

    @Transactional
    public void deleteCategoryInPlan(CategoryInPlan categoryInPlan) {
        categoryInPlanRepository.delete(categoryInPlan);
    }

    @Transactional
    public List<PlanCategoryResponse> modifyCategoryInPlan(Plan plan,
                                                           List<PlanCategoryUpdateRequest> requests) {
        List<PlanCategoryResponse> updatePlanCategories = requests.stream()
            .map(request -> planCategoryService.findByContent(request.content()))
            .toList();

        findAllByPlan(plan).stream()
            .filter(categoryInPlan -> categoryInPlan.exists(updatePlanCategories.stream()
                .map(PlanCategoryResponse::id)
                .toList()))
            .forEach(this::deleteCategoryInPlan);

        return updatePlanCategories;
    }
}
