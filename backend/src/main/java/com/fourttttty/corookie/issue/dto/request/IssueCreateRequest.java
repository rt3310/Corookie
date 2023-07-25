package com.fourttttty.corookie.issue.dto.request;

import com.fourttttty.corookie.issue.domain.Issue;
import com.fourttttty.corookie.issue.domain.IssuePriority;
import com.fourttttty.corookie.issue.domain.IssueProgress;
import com.fourttttty.corookie.member.domain.Member;
import lombok.Builder;

import java.util.List;

@Builder
public record IssueCreateRequest(String topic,
                                 String description,
                                 String progress,
                                 String priority,
                                 List<IssueCategoryCreateRequest> issueCategories) {

    public Issue toEntity(Member member) {
        return Issue.builder()
                .topic(topic)
                .description(description)
                .progress(IssueProgress.from(progress))
                .priority(IssuePriority.from(priority))
                .enabled(true)
                .manager(member)
                .build();
    }
}
