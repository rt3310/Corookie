package com.fourttttty.corookie.plan.application.repository;

import com.fourttttty.corookie.plan.domain.CategoryInPlan;
import com.fourttttty.corookie.plan.domain.Plan;
import java.util.List;

public interface CategoryInPlanRepository {
    void save(CategoryInPlan categoryInPlan);
    List<CategoryInPlan> findAllbyPlan(Plan plan);
}
