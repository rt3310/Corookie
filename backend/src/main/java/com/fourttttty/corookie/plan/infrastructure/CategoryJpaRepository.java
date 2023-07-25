package com.fourttttty.corookie.plan.infrastructure;

import com.fourttttty.corookie.plan.domain.Category;
import com.fourttttty.corookie.plan.domain.Plan;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<Category, Long> {
    List<Category> findByPlan(Plan plan);
    void deleteByPlan(Plan plan);
    Optional<Category> findByCategoryContentAndPlan(String content, Plan plan);
}
