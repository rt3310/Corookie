package com.fourttttty.corookie.issue.dto.response;

import com.fourttttty.corookie.issue.domain.Issue;
import lombok.Builder;

import java.util.List;

@Builder
public record IssueListResponse(String topic,
                                String progress,
                                String priority,
                                List<IssueCategoryResponse> issueCategories,
                                Long projectId,
                                Long memberId) {

    public static IssueListResponse of(Issue issue,
                                       List<IssueCategoryResponse> issueCategories,
                                       Long projectId,
                                       Long memberId) {
        return IssueListResponse.builder()
                .topic(issue.getTopic())
                .progress(issue.getProgress().getValue())
                .priority(issue.getPriority().getValue())
                .issueCategories(issueCategories)
                .projectId(projectId)
                .memberId(memberId)
                .build();
    }
}
