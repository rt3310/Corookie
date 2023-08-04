package com.fourttttty.corookie.issue.application.repository;

import com.fourttttty.corookie.issue.domain.Category;
import com.fourttttty.corookie.issue.domain.Issue;
import com.fourttttty.corookie.issue.domain.IssueCategory;
import com.fourttttty.corookie.issue.infrastructure.IssueCategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class IssueCategoryRepositoryImpl implements IssueCategoryRepository {
    private final IssueCategoryJpaRepository issueCategoryJpaRepository;

    @Override
    public IssueCategory save(IssueCategory issueCategory) {
        return issueCategoryJpaRepository.save(issueCategory);
    }

    @Override
    public Optional<IssueCategory> findByCategory(Category category) {
        return issueCategoryJpaRepository.findByCategory(category);
    }

    @Override
    public List<IssueCategory> findByIssue(Issue issue) {
        return issueCategoryJpaRepository.findByIssue(issue);
    }
}
