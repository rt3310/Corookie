package com.fourttttty.corookie.plan.dto.request;

import com.fourttttty.corookie.plan.domain.Category;
import com.fourttttty.corookie.plan.domain.Plan;
import jakarta.validation.constraints.NotNull;

public record CategoryUpdateRequest(@NotNull String categoryContent) {
    public Category toEntity(Plan plan) {
        return new Category(categoryContent, plan);
    }

}
