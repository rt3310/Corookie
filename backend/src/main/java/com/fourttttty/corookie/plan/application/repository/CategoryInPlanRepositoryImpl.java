package com.fourttttty.corookie.plan.application.repository;

import com.fourttttty.corookie.plan.domain.CategoryInPlan;
import com.fourttttty.corookie.plan.domain.CategoryInPlanId;
import com.fourttttty.corookie.plan.infrastructure.CategoryInPlanJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryInPlanRepositoryImpl implements CategoryInPlanRepository {

    private final CategoryInPlanJpaRepository categoryInPlanJpaRepository;

    @Override
    public CategoryInPlan save(CategoryInPlan categoryInPlan) {
        return categoryInPlanJpaRepository.save(categoryInPlan);
    }

    @Override
    public void delete(CategoryInPlan categoryInPlan) {
        categoryInPlanJpaRepository.delete(categoryInPlan);
    }

    @Override
    public List<CategoryInPlan> findByPlanId(Long planId) {
        return categoryInPlanJpaRepository.findAllByIdPlanId(planId);
    }

    @Override
    public Boolean exists(CategoryInPlanId categoryInPlanId) {
        return categoryInPlanJpaRepository.existsById(categoryInPlanId);
    }
}
