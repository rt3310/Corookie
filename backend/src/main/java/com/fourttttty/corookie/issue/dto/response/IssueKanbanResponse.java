package com.fourttttty.corookie.issue.dto.response;

import java.util.List;

public record IssueKanbanResponse(String topic,
                                  String progress,
                                  String priority,
                                  List<IssueCategoryResponse> issueCategories,
                                  Long projectId,
                                  Long memberId) {
}
