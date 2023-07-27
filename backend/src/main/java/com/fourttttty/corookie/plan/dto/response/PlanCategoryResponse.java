package com.fourttttty.corookie.plan.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlanCategoryResponse(
        @NotNull Long id,
        @NotBlank String content) {
    public static PlanCategoryResponse from(Long id, String content) {
        return new PlanCategoryResponse(id, content);
    }
}
