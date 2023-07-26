package com.fourttttty.corookie.issue.infrastructure;

import com.fourttttty.corookie.issue.domain.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueJpaRepository extends JpaRepository<Issue, Long> {
}
