package com.fourttttty.corookie.plan.application.service;

import com.fourttttty.corookie.plan.application.repository.CategoryRepository;
import com.fourttttty.corookie.plan.dto.request.CategoryRequest;
import com.fourttttty.corookie.plan.dto.response.CategoryResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryService {
  private final CategoryRepository categoryRepository;
  private final PlanService planService;

  public List<CategoryResponse> findCategoriesByPlan(Long planId){
    return categoryRepository.findByPlanId(planId).stream()
        .map(Category -> CategoryResponse.of(Category))
        .toList();
  }

}
