package com.fourttttty.corookie.plan.application.repository;

import com.fourttttty.corookie.plan.domain.PlanCategory;
import com.fourttttty.corookie.plan.domain.Plan;
import java.util.List;
import java.util.Optional;

public interface PlanCategoryRepository {
    PlanCategory save(PlanCategory planCategory);
    Optional<PlanCategory> findById(Long id);
    Optional<PlanCategory> findByContent(String content);

}
