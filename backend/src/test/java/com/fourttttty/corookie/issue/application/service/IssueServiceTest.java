package com.fourttttty.corookie.issue.application.service;

import com.fourttttty.corookie.issue.application.repository.IssueCategoryRepository;
import com.fourttttty.corookie.issue.application.repository.IssueRepository;
import com.fourttttty.corookie.issue.domain.Category;
import com.fourttttty.corookie.issue.domain.IssuePriority;
import com.fourttttty.corookie.issue.domain.IssueProgress;
import com.fourttttty.corookie.issue.dto.request.IssueCategoryCreateRequest;
import com.fourttttty.corookie.issue.dto.response.IssueDetailResponse;
import com.fourttttty.corookie.issue.dto.response.IssueListResponse;
import com.fourttttty.corookie.member.application.repository.MemberRepository;
import com.fourttttty.corookie.member.domain.AuthProvider;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.member.domain.Oauth2;
import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.texture.issue.application.repository.FakeIssueCategoryRepository;
import com.fourttttty.corookie.texture.issue.application.repository.FakeIssueRepository;
import com.fourttttty.corookie.issue.dto.request.IssueCreateRequest;
import com.fourttttty.corookie.texture.member.application.repository.FakeMemberRepository;
import com.fourttttty.corookie.texture.project.application.repository.FakeProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class IssueServiceTest {
    IssueRepository issueRepository;
    ProjectRepository projectRepository;
    MemberRepository memberRepository;
    IssueCategoryRepository issueCategoryRepository;
    IssueService issueService;

    private Member member;
    private Project project;

    @BeforeEach
    void initObjects() {
        projectRepository = new FakeProjectRepository();
        issueRepository = new FakeIssueRepository(projectRepository);
        issueCategoryRepository = new FakeIssueCategoryRepository();
        memberRepository = new FakeMemberRepository();
        issueService = new IssueService(issueRepository, projectRepository, memberRepository,
                new IssueCategoryService(issueCategoryRepository));
        member = Member.of("name", "test@gmail.com", Oauth2.of(AuthProvider.KAKAO, "account"));
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
        assertThat(response.topic()).isEqualTo(request.topic());
        assertThat(response.description()).isEqualTo(request.description());
        assertThat(response.progress()).isEqualTo(request.progress().getValue());
        assertThat(response.priority()).isEqualTo(request.priority().getValue());
        assertThat(response.issueCategories().get(0).category())
                .isEqualTo(request.issueCategories().get(0).category().getValue());
    }

    @Test
    @DisplayName("IssueId로 이슈를 조회한다")
    void findById() {
        // given
        Long memberId = 1L;
        Long projectId = 1L;
        Long issueId = 1L;
        IssueCreateRequest request = new IssueCreateRequest("topic",
                "description",
                IssueProgress.TODO,
                IssuePriority.HIGH,
                List.of(new IssueCategoryCreateRequest(Category.BACKEND)));
        memberRepository.save(member);
        projectRepository.save(project);
        issueService.create(request, projectId, memberId);

        // when
        IssueDetailResponse response = issueService.findById(issueId);

        assertThat(response.topic()).isEqualTo(request.topic());
        assertThat(response.description()).isEqualTo(request.description());
        assertThat(response.progress()).isEqualTo(request.progress().getValue());
        assertThat(response.priority()).isEqualTo(request.priority().getValue());
        assertThat(response.issueCategories().get(0).category())
                .isEqualTo(request.issueCategories().get(0).category().getValue());
    }

    @Test
    @DisplayName("ProjectId로 이슈를 조회한다")
    void findByProjectId() {
        // given
        Long memberId = 1L;
        Long projectId = 1L;
        IssueCreateRequest request = new IssueCreateRequest("topic",
                "description",
                IssueProgress.TODO,
                IssuePriority.HIGH,
                List.of(new IssueCategoryCreateRequest(Category.BACKEND)));
        memberRepository.save(member);
        projectRepository.save(project);
        issueService.create(request, projectId, memberId);

        // when
        List<IssueListResponse> findResponses = issueService.findByProjectId(projectId);

        // then
        assertThat(findResponses.size()).isEqualTo(1L);
        assertThat(findResponses.get(0).topic()).isEqualTo(request.topic());
        assertThat(findResponses.get(0).progress()).isEqualTo(request.progress().getValue());
        assertThat(findResponses.get(0).priority()).isEqualTo(request.priority().getValue());
        assertThat(findResponses.get(0).issueCategories().get(0).category())
                .isEqualTo(request.issueCategories().get(0).category().getValue());
    }
}