package com.fourttttty.corookie.issue.application.repository;

import com.fourttttty.corookie.issue.domain.Category;
import com.fourttttty.corookie.issue.domain.Issue;
import com.fourttttty.corookie.issue.domain.IssueCategory;

import java.util.List;
import java.util.Optional;

public interface IssueCategoryRepository {
    IssueCategory save(IssueCategory issueCategory);
    Optional<IssueCategory> findByCategory(Category category);
    List<IssueCategory> findByIssue(Issue issue);
}
