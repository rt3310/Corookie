package com.fourttttty.corookie.plan.application.service;

import com.fourttttty.corookie.plan.application.repository.CategoryInPlanRepository;
import com.fourttttty.corookie.plan.application.repository.PlanRepository;
import com.fourttttty.corookie.plan.domain.CategoryInPlan;
import com.fourttttty.corookie.plan.domain.Plan;
import com.fourttttty.corookie.plan.domain.PlanCategory;
import com.fourttttty.corookie.plan.dto.request.PlanCategoryCreateRequest;
import com.fourttttty.corookie.plan.dto.response.PlanCategoryResponse;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryInPlanService {

    private final CategoryInPlanRepository categoryInPlanRepository;
    private final PlanRepository planRepository;
    private final PlanCategoryService planCategoryService;

    @Transactional
    public PlanCategoryResponse create(Long projectId, Plan plan, PlanCategoryCreateRequest request) {
        PlanCategory newPlanCategory = planCategoryService.create(request, projectId);
        categoryInPlanRepository.save(CategoryInPlan.of(plan,newPlanCategory));
        return PlanCategoryResponse.from(newPlanCategory);
    }

    public List<CategoryInPlan> findAllByPlanId(Long planId) {
        return categoryInPlanRepository.findByPlanId(planId);
    }

    @Transactional
    public void deleteCategoryInPlan(CategoryInPlan categoryInPlan) {
        categoryInPlanRepository.delete(categoryInPlan);
    }
}
