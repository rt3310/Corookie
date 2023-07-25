package com.fourttttty.corookie.issue.infrastructure;

import com.fourttttty.corookie.issue.domain.IssueCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueCategoryJpaRepository extends JpaRepository<IssueCategory, Long> {

    List<IssueCategory> findByIssueId(Long issueId);
}
