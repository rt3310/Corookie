package com.fourttttty.corookie.issue.dto.request;

import com.fourttttty.corookie.issue.domain.Issue;
import com.fourttttty.corookie.issue.domain.IssueCategory;
import jakarta.validation.constraints.NotBlank;

public record IssueCategoryCreateRequest(@NotBlank String category) {

    public IssueCategory toEntity(Issue issue) {
        return new IssueCategory(category, issue);
    }
}
