package com.fourttttty.corookie.plan.infrastructure;

import com.fourttttty.corookie.plan.domain.PlanCategory;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanCategoryJpaRepository extends JpaRepository<PlanCategory, Long> {
    Optional<PlanCategory> findByContent(String content);
}
