package com.fourttttty.corookie.plan.dto.response;

import jakarta.validation.constraints.NotBlank;

public record PlanCategoryResponse(@NotBlank String content) {
}
