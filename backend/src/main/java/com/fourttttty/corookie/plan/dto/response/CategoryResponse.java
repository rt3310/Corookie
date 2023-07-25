package com.fourttttty.corookie.plan.dto.response;

import com.fourttttty.corookie.plan.domain.Category;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CategoryResponse(@NotNull String categoryContent) {
    public static CategoryResponse of(Category category){
        return CategoryResponse.builder()
            .categoryContent(category.getCategoryContent())
            .build();
    }
}
