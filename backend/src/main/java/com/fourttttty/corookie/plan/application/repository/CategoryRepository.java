package com.fourttttty.corookie.plan.application.repository;

import com.fourttttty.corookie.plan.domain.Category;
import com.fourttttty.corookie.plan.domain.Plan;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Category save(Category category);
    List<Category> findAllByPlan(Plan plan);
    void deleteAllByPlan(Plan plan);
    List<Category> saveCategories(List<Category> categories);
    Optional<Category> findByContentANDPlan(String content, Plan plan);
}
