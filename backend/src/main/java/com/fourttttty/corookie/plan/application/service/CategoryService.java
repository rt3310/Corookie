package com.fourttttty.corookie.plan.application.service;

import com.fourttttty.corookie.plan.application.repository.CategoryRepository;
import com.fourttttty.corookie.plan.domain.Category;
import com.fourttttty.corookie.plan.domain.Plan;
import com.fourttttty.corookie.plan.dto.request.CategoryCreateRequest;
import com.fourttttty.corookie.plan.dto.response.CategoryResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryResponse create(Category category) {
        return CategoryResponse.of(categoryRepository.save(category));
    }

    public Boolean findByCategoryContentAndPlan(String content, Plan plan) {
        return categoryRepository.findByContentANDPlan(content, plan).isEmpty();
    }

    public List<CategoryResponse> findCategoriesByPlan(Plan plan) {
        return categoryRepository.findAllByPlan(plan).stream()
            .map(category -> CategoryResponse.of(category))
            .toList();
    }

    public List<CategoryResponse> createCategoriesByPlan(Plan plan, List<CategoryCreateRequest> categories) {;
        return categoryRepository.saveCategories(categories.stream()
                .distinct()
                .map(category->category.toEntity(plan))
                .toList()).stream()
            .map(category -> CategoryResponse.of(category))
            .toList();
    }

}
