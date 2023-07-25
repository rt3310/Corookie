package com.fourttttty.corookie.issue.dto.response;

import com.fourttttty.corookie.issue.domain.Issue;
import lombok.Builder;

import java.util.List;

@Builder
public record IssueDetailResponse(String topic,
                                  String description,
                                  String progress,
                                  String priority,
                                  List<IssueCategoryResponse> issueCategories,
                                  Long projectId,
                                  Long memberId) {

    public static IssueDetailResponse of(Issue issue,
                                         List<IssueCategoryResponse> issueCategories,
                                         Long projectId,
                                         Long memberId) {
        return IssueDetailResponse.builder()
                .topic(issue.getTopic())
                .description(issue.getDescription())
                .progress(issue.getProgress().getValue())
                .priority(issue.getPriority().getValue())
                .issueCategories(issueCategories)
                .projectId(projectId)
                .memberId(memberId)
                .build();
    }
}
