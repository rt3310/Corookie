package com.fourttttty.corookie.issue.dto.response;

import com.fourttttty.corookie.issue.domain.Issue;
import lombok.Builder;

import java.util.List;

@Builder
public record IssueListResponse(String topic,
                                String progress,
                                String priority,
                                List<IssueCategoryResponse> issueCategories,
                                String memberName) {

    public static IssueListResponse from(Issue issue,
                                         List<IssueCategoryResponse> issueCategories) {
        return IssueListResponse.builder()
                .topic(issue.getTopic())
                .progress(issue.getProgress().getValue())
                .priority(issue.getPriority().getName())
                .issueCategories(issueCategories)
                .memberName(issue.getManager().getName())
                .build();
    }
}
