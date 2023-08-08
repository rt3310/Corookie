package com.fourttttty.corookie.issue.dto.response;

import com.fourttttty.corookie.issue.domain.IssueCategory;
import com.fourttttty.corookie.issue.domain.Issue;
import com.fourttttty.corookie.issue.domain.IssuePriority;
import com.fourttttty.corookie.issue.domain.IssueProgress;
import lombok.Builder;

@Builder
public record IssueListResponse(String topic,
                                IssueProgress progress,
                                IssuePriority priority,
                                IssueCategory category,
                                String memberName) {

    public static IssueListResponse from(Issue issue) {
        return IssueListResponse.builder()
                .topic(issue.getTopic())
                .progress(issue.getProgress())
                .priority(issue.getPriority())
                .category(issue.getIssueCategory())
                .memberName(issue.getManager().getName())
                .build();
    }
}
