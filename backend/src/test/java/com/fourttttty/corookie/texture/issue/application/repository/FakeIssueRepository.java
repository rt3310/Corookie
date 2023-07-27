package com.fourttttty.corookie.texture.issue.application.repository;

import com.fourttttty.corookie.issue.application.repository.IssueRepository;
import com.fourttttty.corookie.issue.domain.Issue;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FakeIssueRepository implements IssueRepository {
    private long autoIncrementId = 1L;
    private final Map<Long, Issue> store = new HashMap<>();

    @Override
    public Issue save(Issue issue) {
        store.put(autoIncrementId++, issue);
        return issue;
    }

    @Override
    public Optional<Issue> findById(Long issueId) {
        return Optional.ofNullable(store.get(issueId));
    }

    @Override
    public List<Issue> findByProjectId(Long projectId) {
        return store.entrySet().stream()
                .filter(entry -> entry.getValue().getProject().getId().equals(projectId))
                .map(entry -> store.get(entry.getKey()))
                .toList();
    }

    @Override
    public void deleteById(Long issueId) {
        store.remove(issueId);
    }
}
