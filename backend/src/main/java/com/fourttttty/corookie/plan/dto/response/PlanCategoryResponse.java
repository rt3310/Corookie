package com.fourttttty.corookie.plan.dto.response;

import com.fourttttty.corookie.plan.domain.PlanCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlanCategoryResponse(Long id,
                                   String content) {
    public static PlanCategoryResponse from(PlanCategory planCategory) {
        return new PlanCategoryResponse(planCategory.getId(), planCategory.getContent());
    }
}
