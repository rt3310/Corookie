package com.fourttttty.corookie.issue.dto.response;

import com.fourttttty.corookie.issue.domain.Issue;
import com.fourttttty.corookie.issue.domain.IssuePriority;
import com.fourttttty.corookie.issue.domain.IssueProgress;
import lombok.Builder;

import java.util.List;

@Builder
public record IssueDetailResponse(String topic,
                                  String description,
                                  IssueProgress progress,
                                  IssuePriority priority,
                                  List<IssueCategoryResponse> issueCategories) {

    public static IssueDetailResponse from(Issue issue,
                                           List<IssueCategoryResponse> issueCategories) {
        return IssueDetailResponse.builder()
                .topic(issue.getTopic())
                .description(issue.getDescription())
                .progress(issue.getProgress())
                .priority(issue.getPriority())
                .issueCategories(issueCategories)
                .build();
    }
}
