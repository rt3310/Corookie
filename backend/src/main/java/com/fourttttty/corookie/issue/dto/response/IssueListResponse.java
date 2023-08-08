package com.fourttttty.corookie.issue.dto.response;

import com.fourttttty.corookie.issue.domain.Issue;
import com.fourttttty.corookie.issue.domain.IssuePriority;
import com.fourttttty.corookie.issue.domain.IssueProgress;
import lombok.Builder;

import java.util.List;

@Builder
public record IssueListResponse(String topic,
                                IssueProgress progress,
                                IssuePriority priority,
                                List<IssueCategoryResponse> issueCategories,
                                String memberName) {

    public static IssueListResponse from(Issue issue,
                                         List<IssueCategoryResponse> issueCategories) {
        return IssueListResponse.builder()
                .topic(issue.getTopic())
                .progress(issue.getProgress())
                .priority(issue.getPriority())
                .issueCategories(issueCategories)
                .memberName(issue.getManager().getName())
                .build();
    }
}
