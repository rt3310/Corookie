package com.fourttttty.corookie.issue.application.repository;

import com.fourttttty.corookie.issue.domain.IssueCategory;
import com.fourttttty.corookie.issue.infrastructure.IssueCategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class IssueCategoryRepositoryImpl implements IssueCategoryRepository {
    private final IssueCategoryJpaRepository issueCategoryJpaRepository;

    @Override
    public List<IssueCategory> findByIssueId(Long issueId) {
        return issueCategoryJpaRepository.findByIssueId(issueId);
    }
}
