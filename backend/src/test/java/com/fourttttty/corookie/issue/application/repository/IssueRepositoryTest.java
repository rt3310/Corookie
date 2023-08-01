package com.fourttttty.corookie.issue.application.repository;

import com.fourttttty.corookie.issue.domain.Issue;
import com.fourttttty.corookie.issue.domain.IssuePriority;
import com.fourttttty.corookie.issue.domain.IssueProgress;
import com.fourttttty.corookie.member.domain.AuthProvider;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.member.domain.Oauth2;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.texture.issue.application.repository.FakeIssueRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class IssueRepositoryTest {
    IssueRepository issueRepository;
    Member member;
    Project project;

    @BeforeEach
    void initObjects() {
        issueRepository = new FakeIssueRepository();
        member = Member.of("name", Oauth2.of(AuthProvider.KAKAO, "account"));
        project = Project.of("name",
                "description",
                true,
                "http://test.com",
                false,
                member);
    }

    @Test
    @DisplayName("이슈를 저장한다")
    void saveIssue() {
        // given
        Issue issue = Issue.of("topic",
                "description",
                IssueProgress.TODO,
                IssuePriority.HIGH,
                true,
                project,
                member);

        // when
        Issue savedIssue = issueRepository.save(issue);

        // then
        assertThat(savedIssue.getTopic()).isEqualTo(issue.getTopic());
        assertThat(savedIssue.getDescription()).isEqualTo(issue.getDescription());
        assertThat(savedIssue.getProgress()).isEqualTo(issue.getProgress());
        assertThat(savedIssue.getPriority()).isEqualTo(issue.getPriority());
        assertThat(savedIssue.getEnabled()).isEqualTo(issue.getEnabled());
    }
}