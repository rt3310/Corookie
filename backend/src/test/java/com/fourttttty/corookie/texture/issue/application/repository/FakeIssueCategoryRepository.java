package com.fourttttty.corookie.texture.issue.application.repository;

import com.fourttttty.corookie.global.exception.IssueNotFoundException;
import com.fourttttty.corookie.issue.application.repository.IssueCategoryRepository;
import com.fourttttty.corookie.issue.application.repository.IssueRepository;
import com.fourttttty.corookie.issue.domain.Category;
import com.fourttttty.corookie.issue.domain.Issue;
import com.fourttttty.corookie.issue.domain.IssueCategory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FakeIssueCategoryRepository implements IssueCategoryRepository {

    private long autoIncrementId = 1L;
    private final Map<Long, IssueCategory> store = new HashMap<>();

    @Override
    public IssueCategory save(IssueCategory issueCategory) {
        store.put(autoIncrementId++, issueCategory);
        return issueCategory;
    }

    @Override
    public Optional<IssueCategory> findByCategory(Category category) {
        return store.entrySet().stream()
                .filter(entry -> entry.getValue().getCategory().getValue().equals(category.getValue()))
                .map(entry -> store.get(entry.getKey()))
                .findAny();
    }

    @Override
    public List<IssueCategory> findByIssue(Issue issue) {
        return store.values().stream()
                .filter(issueCategory -> issueCategory.getIssue().equals(issue))
                .toList();
    }
}
