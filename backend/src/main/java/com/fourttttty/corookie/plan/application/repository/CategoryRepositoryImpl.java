package com.fourttttty.corookie.plan.application.repository;

import com.fourttttty.corookie.plan.domain.Category;
import com.fourttttty.corookie.plan.infrastructure.CategoryJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {
  private final CategoryJpaRepository categoryJpaRepository;

  @Override
  public List<Category> findByPlanId(Long planId) {
    return categoryJpaRepository.findByPlanId(planId);
  }
}
