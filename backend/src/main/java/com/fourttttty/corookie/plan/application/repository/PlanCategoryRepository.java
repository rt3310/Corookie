package com.fourttttty.corookie.plan.application.repository;

import com.fourttttty.corookie.plan.domain.PlanCategory;
import com.fourttttty.corookie.plan.domain.Plan;
import java.util.List;
import java.util.Optional;

public interface PlanCategoryRepository {
    PlanCategory save(PlanCategory planCategory);
    List<PlanCategory> findAllByPlan(Plan plan);
    void deleteAllByPlan(Plan plan);
    List<PlanCategory> savePlanCategories(List<PlanCategory> planCategories);
    Optional<PlanCategory> findByContentAndPlan(String content, Plan plan);
}
