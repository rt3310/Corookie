package com.fourttttty.corookie.plan.infrastructure;

import com.fourttttty.corookie.plan.domain.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<Category, Long> {
  List<Category> findByPlanId(Long planId);
}
