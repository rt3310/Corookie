package com.fourttttty.corookie.issue.application.repository;

import com.fourttttty.corookie.issue.domain.Issue;
import com.fourttttty.corookie.issue.domain.IssuePriority;
import com.fourttttty.corookie.issue.domain.IssueProgress;
import com.fourttttty.corookie.member.domain.AuthProvider;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.member.domain.Oauth2;
import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.texture.issue.application.repository.FakeIssueRepository;
import com.fourttttty.corookie.texture.project.application.repository.FakeProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class IssueRepositoryTest {
    IssueRepository issueRepository;
    ProjectRepository projectRepository;
    Member member;
    Project project;

    @BeforeEach
    void initObjects() {
        projectRepository = new FakeProjectRepository();
        issueRepository = new FakeIssueRepository(projectRepository);
        member = Member.of("name", "test@gmail.com", Oauth2.of(AuthProvider.KAKAO, "account"));
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

    @Test
    @DisplayName("issueId로 이슈를 조회한다")
    void findById() {
        // given
        Long issueId = 1L;
        Issue issue = Issue.of("topic",
                "description",
                IssueProgress.TODO,
                IssuePriority.HIGH,
                true,
                project,
                member);
        issueRepository.save(issue);

        // when
        Optional<Issue> findIssue = issueRepository.findById(issueId);

        // then
        assertThat(findIssue).isNotEmpty();
        assertThat(findIssue.get().getTopic()).isEqualTo(issue.getTopic());
        assertThat(findIssue.get().getDescription()).isEqualTo(issue.getDescription());
        assertThat(findIssue.get().getProgress()).isEqualTo(issue.getProgress());
        assertThat(findIssue.get().getPriority()).isEqualTo(issue.getPriority());
        assertThat(findIssue.get().getEnabled()).isEqualTo(issue.getEnabled());
        assertThat(findIssue.get().getProject()).isEqualTo(issue.getProject());
        assertThat(findIssue.get().getManager()).isEqualTo(issue.getManager());
    }

    @Test
    @DisplayName("projectId로 이슈 목록을 조회한다")
    void findByProjectId() {
        // given
        Long projectId = 1L;
        projectRepository.save(project);
        Issue issue = Issue.of("topic",
                "description",
                IssueProgress.TODO,
                IssuePriority.HIGH,
                true,
                project,
                member);
        issueRepository.save(issue);

        // when
        List<Issue> findIssue = issueRepository.findByProjectId(projectId);

        // then
        assertThat(findIssue.size()).isEqualTo(1L);
        assertThat(findIssue.get(0).getTopic()).isEqualTo(issue.getTopic());
        assertThat(findIssue.get(0).getDescription()).isEqualTo(issue.getDescription());
        assertThat(findIssue.get(0).getProgress()).isEqualTo(issue.getProgress());
        assertThat(findIssue.get(0).getPriority()).isEqualTo(issue.getPriority());
        assertThat(findIssue.get(0).getEnabled()).isEqualTo(issue.getEnabled());
        assertThat(findIssue.get(0).getProject()).isEqualTo(issue.getProject());
        assertThat(findIssue.get(0).getManager()).isEqualTo(issue.getManager());
    }

    @Test
    @DisplayName("IssueId로 이슈를 삭제한다")
    void deleteById() {
        // given
        Long issueId = 1L;
        Issue issue = Issue.of("topic",
                "description",
                IssueProgress.TODO,
                IssuePriority.HIGH,
                true,
                project,
                member);
        issueRepository.save(issue);

        // when
        issueRepository.deleteById(issueId);

        // then
        assertThat(issueRepository.findById(issueId)).isEmpty();
    }
}