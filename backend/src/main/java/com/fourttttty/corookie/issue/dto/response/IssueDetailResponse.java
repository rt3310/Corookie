package com.fourttttty.corookie.issue.dto.response;

import com.fourttttty.corookie.issue.domain.Issue;
import lombok.Builder;

import java.util.List;

@Builder
public record IssueDetailResponse(String topic,
                                  String description,
                                  String progress,
                                  String priority,
                                  List<IssueCategoryResponse> issueCategories) {

    public static IssueDetailResponse from(Issue issue,
                                           List<IssueCategoryResponse> issueCategories) {
        return IssueDetailResponse.builder()
                .topic(issue.getTopic())
                .description(issue.getDescription())
                .progress(issue.getProgress().getValue())
                .priority(issue.getPriority().getName())
                .issueCategories(issueCategories)
                .build();
    }
}
