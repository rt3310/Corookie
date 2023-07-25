package com.fourttttty.corookie.issue.application.repository;

import com.fourttttty.corookie.issue.domain.Issue;
import com.fourttttty.corookie.issue.infrastructure.IssueJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class IssueRepositoryImpl implements IssueRepository {
    private final IssueJpaRepository issueJpaRepository;

    public Issue save(Issue issue) {
        return issueJpaRepository.save(issue);
    }

    public Optional<Issue> findById(Long id) {
        return issueJpaRepository.findById(id);
    }

    public List<Issue> findByProjectId(Long projectId) {
        return issueJpaRepository.findAll();
    }
}
