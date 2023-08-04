package com.fourttttty.corookie.texture.issue.application.repository;

import com.fourttttty.corookie.global.exception.ProjectNotFoundException;
import com.fourttttty.corookie.issue.application.repository.IssueRepository;
import com.fourttttty.corookie.issue.domain.Issue;
import com.fourttttty.corookie.project.application.repository.ProjectRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FakeIssueRepository implements IssueRepository {
    private long autoIncrementId = 1L;
    private final Map<Long, Issue> store = new HashMap<>();
    private final ProjectRepository projectRepository;

    public FakeIssueRepository(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

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
                .filter(entry -> entry.getValue().getProject()
                        .equals(projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new)))
                .map(entry -> store.get(entry.getKey()))
                .toList();
    }

    @Override
    public void deleteById(Long issueId) {
        store.remove(issueId);
    }
}
