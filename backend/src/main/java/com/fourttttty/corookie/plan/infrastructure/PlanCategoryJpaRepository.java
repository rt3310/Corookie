package com.fourttttty.corookie.plan.infrastructure;

import com.fourttttty.corookie.plan.domain.PlanCategory;
import com.fourttttty.corookie.plan.domain.Plan;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanCategoryJpaRepository extends JpaRepository<PlanCategory, Long> {
    List<PlanCategory> findByPlan(Plan plan);
    void deleteByPlan(Plan plan);
    Optional<PlanCategory> findByContentAndPlan(String content, Plan plan);
}
