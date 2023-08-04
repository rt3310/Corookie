package com.fourttttty.corookie.issue.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "issue_category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IssueCategory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "issue_category_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issue_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Issue issue;

    private IssueCategory(Category category, Issue issue) {
        this.category = category;
        this.issue = issue;
    }

    public static IssueCategory of(Category category, Issue issue) {
        return new IssueCategory(category, issue);
    }
}
