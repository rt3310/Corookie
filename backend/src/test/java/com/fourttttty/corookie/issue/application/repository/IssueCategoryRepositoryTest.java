package com.fourttttty.corookie.issue.application.repository;

import com.fourttttty.corookie.issue.domain.*;
import com.fourttttty.corookie.member.domain.AuthProvider;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.member.domain.Oauth2;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.texture.issue.application.repository.FakeIssueCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IssueCategoryRepositoryTest {
    IssueCategoryRepository issueCategoryRepository;
    Issue issue;
    Project project;
    Member member;

    @BeforeEach
    void initObjects() {
        issueCategoryRepository = new FakeIssueCategoryRepository();
        member = Member.of("name", "test@gmail.com", Oauth2.of(AuthProvider.KAKAO, "account"));
        project = Project.of("name",
                "description",
                true,
                "http://test.com",
                false,
                member);
        issue = Issue.of("topic",
                "description",
                IssueProgress.TODO,
                IssuePriority.HIGH,
                true,
                project,
                member);
    }

    @Test
    @DisplayName("이슈 카테고리를 저장한다")
    void save() {
        // given
        IssueCategory.of(Category.BACKEND, issue);
    }
}