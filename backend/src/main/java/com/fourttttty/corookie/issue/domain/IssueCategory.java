package com.fourttttty.corookie.issue.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "issue_category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IssueCategory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issue_category_id", nullable = false)
    private Long id;

    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_id")
    private Issue issue;

    public IssueCategory(String category, Issue issue) {
        this.category = category;
        this.issue = issue;
    }
}
