package com.fourttttty.corookie.issue.application.repository;

import com.fourttttty.corookie.issue.domain.IssueCategory;

import java.util.List;

public interface IssueCategoryRepository {
    public List<IssueCategory> findByIssueId(Long issueId);
}
