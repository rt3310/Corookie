package com.fourttttty.corookie.issue.dto.response;

import com.fourttttty.corookie.issue.domain.Category;

public record IssueCategoryResponse(String category) {

    public static IssueCategoryResponse from(Category category) {
        return new IssueCategoryResponse(category.getValue());
    }
}