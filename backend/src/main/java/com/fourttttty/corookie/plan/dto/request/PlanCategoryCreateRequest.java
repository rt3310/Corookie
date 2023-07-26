package com.fourttttty.corookie.plan.dto.request;

import com.fourttttty.corookie.plan.domain.PlanCategory;
import com.fourttttty.corookie.plan.domain.Plan;
import jakarta.validation.constraints.NotBlank;

public record PlanCategoryCreateRequest(@NotBlank String content) {
    public PlanCategory toEntity() {
        return new PlanCategory(content);
    }
}
