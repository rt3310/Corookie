package com.fourttttty.corookie.issue.dto.response;

import com.fourttttty.corookie.issue.domain.IssueCategory;
import com.fourttttty.corookie.issue.domain.IssuePriority;
import com.fourttttty.corookie.issue.domain.IssueProgress;

public record IssueKanbanResponse(String topic,
                                  IssueProgress progress,
                                  IssuePriority priority,
                                  IssueCategory category,
                                  Long projectId,
                                  Long memberId) {
}
