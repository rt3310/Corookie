package com.fourttttty.corookie.plan.application.repository;

import com.fourttttty.corookie.plan.domain.CategoryInPlan;
import com.fourttttty.corookie.plan.domain.Plan;
import com.fourttttty.corookie.plan.infrastructure.CategoryInPlanJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryInPlanRepositoryImpl implements CategoryInPlanRepository {
    private final CategoryInPlanJpaRepository categoryInPlanJpaRepository;

    @Override
    public void save(CategoryInPlan categoryInPlan) {
        categoryInPlanJpaRepository.save(categoryInPlan);
    }

    @Override
    public List<CategoryInPlan> findAllByPlan(Plan plan) {
        return categoryInPlanJpaRepository.findByIdPlan(plan);
    }
}
