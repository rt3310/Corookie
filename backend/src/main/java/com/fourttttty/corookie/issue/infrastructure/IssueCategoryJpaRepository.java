package com.fourttttty.corookie.issue.infrastructure;

import com.fourttttty.corookie.issue.domain.IssueCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IssueCategoryJpaRepository extends JpaRepository<IssueCategory, Long> {

    Optional<IssueCategory> findByCategory(String category);
    List<IssueCategory> findByIssueId(Long issueId);
}
