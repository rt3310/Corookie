package com.fourttttty.corookie.plan.infrastructure;

import com.fourttttty.corookie.plan.domain.CategoryInPlan;
import com.fourttttty.corookie.plan.domain.CategoryInPlanId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryInPlanJpaRepository extends JpaRepository<CategoryInPlan, CategoryInPlanId> {
    List<CategoryInPlan> findAllByIdPlanId(Long planId);
}
