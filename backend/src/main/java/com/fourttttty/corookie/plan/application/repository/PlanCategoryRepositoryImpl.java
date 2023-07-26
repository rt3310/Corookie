package com.fourttttty.corookie.plan.application.repository;

import com.fourttttty.corookie.plan.domain.PlanCategory;
import com.fourttttty.corookie.plan.domain.Plan;
import com.fourttttty.corookie.plan.infrastructure.PlanCategoryJpaRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PlanCategoryRepositoryImpl implements PlanCategoryRepository {
    private final PlanCategoryJpaRepository planCategoryJpaRepository;

    public PlanCategory save(PlanCategory planCategory) {
        return planCategoryJpaRepository.save(planCategory);
    }

    @Override
    public List<PlanCategory> findAllByPlan(Plan plan) {
        return planCategoryJpaRepository.findByPlan(plan);
    }

    @Override
    public void deleteAllByPlan(Plan plan) {
        planCategoryJpaRepository.deleteByPlan(plan);
    }

    @Override
    public List<PlanCategory> savePlanCategories(List<PlanCategory> planCategories) {
        return planCategoryJpaRepository.saveAll(planCategories);
    }

    @Override
    public Optional<PlanCategory> findByContentAndPlan(String content, Plan plan) {
        return planCategoryJpaRepository.findByContentAndPlan(content, plan);
    }

}
