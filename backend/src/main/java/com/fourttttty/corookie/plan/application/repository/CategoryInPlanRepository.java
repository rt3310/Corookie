package com.fourttttty.corookie.plan.application.repository;

import com.fourttttty.corookie.plan.domain.CategoryInPlan;
import com.fourttttty.corookie.plan.domain.CategoryInPlanId;
import com.fourttttty.corookie.plan.domain.Plan;
import java.util.List;

public interface CategoryInPlanRepository {

    CategoryInPlan save(CategoryInPlan categoryInPlan);

    void delete(CategoryInPlan categoryInPlan);

    List<CategoryInPlan> findAllbyPlan(Plan plan);

    Boolean exists(CategoryInPlanId categoryInPlanId);
}
