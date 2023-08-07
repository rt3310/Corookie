package com.fourttttty.corookie.issue.application.repository;

import com.fourttttty.corookie.issue.domain.Issue;
import com.fourttttty.corookie.issue.domain.IssueProgress;

import java.util.List;
import java.util.Optional;

public interface IssueRepository {
    Issue save(Issue issue);
    Optional<Issue> findById(Long issueId);
    List<Issue> findByProjectId(Long projectId);
    void deleteById(Long issueId);
    List<Issue> findByManager(Long projectId, String managerName);
    List<Issue> findLikeTopic(Long projectId, String topic);
    List<Issue> findAllPriorityAsc(Long projectId);
    List<Issue> findAllPriorityDesc(Long projectId);
    List<Issue> findByProgress(Long projectId, IssueProgress progress);
}
