package com.fourttttty.corookie.plan.application.repository;

import com.fourttttty.corookie.plan.domain.Category;
import com.fourttttty.corookie.plan.domain.Plan;
import com.fourttttty.corookie.plan.infrastructure.CategoryJpaRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {
    private final CategoryJpaRepository categoryJpaRepository;

    public Category save(Category category) {
        return categoryJpaRepository.save(category);
    }

    @Override
    public List<Category> findAllByPlan(Plan plan) {
        return categoryJpaRepository.findByPlan(plan);
    }

    @Override
    public void deleteAllByPlan(Plan plan) {
        categoryJpaRepository.deleteByPlan(plan);
    }

    @Override
    public List<Category> saveCategories(List<Category> categories) {
        return categoryJpaRepository.saveAll(categories);
    }

    @Override
    public Optional<Category> findByContentANDPlan(String content, Plan plan) {
        return categoryJpaRepository.findByCategoryContentAndPlan(content, plan);
    }

}
