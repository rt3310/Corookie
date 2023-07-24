package com.fourttttty.corookie.plan.application.repository;

import com.fourttttty.corookie.plan.domain.Category;
import java.util.List;

public interface CategoryRepository {
  List<Category> findByPlanId(Long planId);
}
