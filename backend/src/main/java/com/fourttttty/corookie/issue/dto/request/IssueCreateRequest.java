package com.fourttttty.corookie.issue.dto.request;

import com.fourttttty.corookie.issue.domain.Issue;
import com.fourttttty.corookie.issue.domain.IssuePriority;
import com.fourttttty.corookie.issue.domain.IssueProgress;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.project.domain.Project;
import lombok.Builder;

import java.util.List;

@Builder
public record IssueCreateRequest(String topic,
                                 String description,
                                 IssueProgress issueProgress,
                                 IssuePriority issuePriority,
                                 List<IssueCategoryCreateRequest> issueCategories) {

    public Issue toEntity(Project project, Member member) {
        return Issue.builder()
                .topic(topic)
                .description(description)
                .progress(issueProgress)
                .priority(issuePriority)
                .enabled(true)
                .project(project)
                .manager(member)
                .build();
    }
}
