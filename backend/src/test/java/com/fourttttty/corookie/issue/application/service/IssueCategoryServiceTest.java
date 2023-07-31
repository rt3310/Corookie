package com.fourttttty.corookie.issue.application.service;

import com.fourttttty.corookie.issue.application.repository.IssueCategoryRepository;
import com.fourttttty.corookie.issue.application.repository.IssueRepository;
import com.fourttttty.corookie.issue.domain.Category;
import com.fourttttty.corookie.issue.domain.IssueCategory;
import com.fourttttty.corookie.issue.domain.IssuePriority;
import com.fourttttty.corookie.issue.domain.IssueProgress;
import com.fourttttty.corookie.issue.dto.request.IssueCategoryCreateRequest;
import com.fourttttty.corookie.issue.dto.response.IssueDetailResponse;
import com.fourttttty.corookie.member.application.repository.MemberRepository;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.texture.issue.application.repository.FakeIssueCategoryRepository;
import com.fourttttty.corookie.texture.issue.application.repository.FakeIssueRepository;
import com.fourttttty.corookie.issue.dto.request.IssueCreateRequest;
import com.fourttttty.corookie.texture.member.application.repository.FakeMemberRepository;
import com.fourttttty.corookie.texture.project.application.repository.FakeProjectRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class IssueCategoryServiceTest {
    IssueRepository issueRepository;
    ProjectRepository projectRepository;
    MemberRepository memberRepository;
    IssueCategoryRepository issueCategoryRepository;
    IssueService issueService;

    private Member member;
    private Project project;

    @BeforeEach
    void initObjects() {
        issueRepository = new FakeIssueRepository();
        projectRepository = new FakeProjectRepository();
        memberRepository = new FakeMemberRepository();
        issueCategoryRepository = new FakeIssueCategoryRepository();
        issueService = new IssueService(issueRepository, projectRepository, memberRepository,
                new IssueCategoryService(issueCategoryRepository));
        member = new Member("name");
        project = Project.of("name", "description", true,
                "http://test.com", false, member);
    }

    @Test
    @DisplayName("이슈를 생성한다")
    void createIssue() {
        // given
        IssueCreateRequest request = new IssueCreateRequest("topic",
                "description",
                IssueProgress.TODO,
                IssuePriority.HIGH,
                List.of(new IssueCategoryCreateRequest(Category.BACKEND)));

        // when
        memberRepository.save(member);
        projectRepository.save(project);
        IssueDetailResponse response = issueService.create(request, 1L, 1L);

        // then
        assertThat(response.topic()).isEqualTo("topic");
        assertThat(response.description()).isEqualTo("description");
        assertThat(response.progress()).isEqualTo(IssueProgress.TODO.getValue());
        assertThat(response.priority()).isEqualTo(IssuePriority.HIGH.getValue());
        assertThat(response.issueCategories().get(0).category()).isEqualTo(Category.BACKEND.getValue());
    }

}