package com.fourttttty.corookie.plan.dto.request;

import com.fourttttty.corookie.plan.domain.PlanCategory;
import jakarta.validation.constraints.NotBlank;

public record PlanCategoryDeleteRequest(@NotBlank String content,
                                        @NotBlank String color) {
}
