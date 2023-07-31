package com.fourttttty.corookie.plan.application.service;

import com.fourttttty.corookie.plan.application.repository.PlanCategoryRepository;
import com.fourttttty.corookie.plan.domain.PlanCategory;
import com.fourttttty.corookie.plan.dto.response.PlanCategoryResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlanCategoryService {
    private final PlanCategoryRepository planCategoryRepository;

    @Transactional
    public PlanCategory create(String content) {
        return planCategoryRepository.findByContent(content)
            .orElse(planCategoryRepository.save(PlanCategory.of(content)));
    }

    public PlanCategory findById(Long id){
        return planCategoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public PlanCategoryResponse findByContent(String content) {
        PlanCategory planCategory = planCategoryRepository.findByContent(content)
            .orElse(planCategoryRepository.save(PlanCategory.of(content)));
        return PlanCategoryResponse.from(planCategory);
    }
}
