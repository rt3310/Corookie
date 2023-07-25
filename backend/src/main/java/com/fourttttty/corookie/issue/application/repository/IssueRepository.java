package com.fourttttty.corookie.issue.application.repository;

import com.fourttttty.corookie.issue.domain.Issue;

import java.util.List;
import java.util.Optional;

public interface IssueRepository {
    Issue save(Issue issue);
    Optional<Issue> findById(Long issueId);
    List<Issue> findByProjectId(Long projectId);
    void deleteById(Long issueId);
}
