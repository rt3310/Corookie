package com.fourttttty.corookie.issue.dto.response;

import com.fourttttty.corookie.issue.domain.IssueCategory;
import com.fourttttty.corookie.issue.domain.Issue;
import com.fourttttty.corookie.issue.domain.IssuePriority;
import com.fourttttty.corookie.issue.domain.IssueProgress;
import lombok.Builder;

@Builder
public record IssueDetailResponse(String topic,
                                  String description,
                                  IssueProgress progress,
                                  IssuePriority priority,
                                  IssueCategory category) {

    public static IssueDetailResponse from(Issue issue) {
        return IssueDetailResponse.builder()
                .topic(issue.getTopic())
                .description(issue.getDescription())
                .progress(issue.getProgress())
                .priority(issue.getPriority())
                .category(issue.getIssueCategory())
                .build();
    }
}
