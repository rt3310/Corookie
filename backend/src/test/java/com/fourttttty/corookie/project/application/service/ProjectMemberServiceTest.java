package com.fourttttty.corookie.project.application.service;

import com.fourttttty.corookie.global.exception.ProjectNotDisabledException;
import com.fourttttty.corookie.global.exception.ProjectNotFoundException;
import com.fourttttty.corookie.member.application.repository.MemberRepository;
import com.fourttttty.corookie.member.domain.AuthProvider;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.member.domain.Oauth2;
import com.fourttttty.corookie.project.application.repository.ProjectMemberRepository;
import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.project.domain.ProjectMember;
import com.fourttttty.corookie.project.domain.ProjectMemberId;
import com.fourttttty.corookie.project.dto.request.ProjectMemberCreateRequest;
import com.fourttttty.corookie.project.dto.response.MemberProjectResponse;
import com.fourttttty.corookie.project.dto.response.ProjectMemberResponse;
import com.fourttttty.corookie.textchannel.application.repository.TextChannelRepository;
import com.fourttttty.corookie.texture.member.application.repository.FakeMemberRepository;
import com.fourttttty.corookie.texture.project.application.repository.FakeProjectMemberRepository;
import com.fourttttty.corookie.texture.project.application.repository.FakeProjectRepository;
import com.fourttttty.corookie.texture.textchannel.application.repository.FakeTextChannelRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ProjectMemberServiceTest {

    ProjectRepository projectRepository;
    MemberRepository memberRepository;
    ProjectMemberRepository projectMemberRepository;
    TextChannelRepository textChannelRepository;
    ProjectMemberService projectMemberService;

    private Member member;
    private Project project;

    @BeforeEach
    void initObjects() {
        projectRepository = new FakeProjectRepository();
        memberRepository = new FakeMemberRepository();
        projectMemberRepository = new FakeProjectMemberRepository(projectRepository, memberRepository);
        textChannelRepository = new FakeTextChannelRepository();
        projectMemberService = new ProjectMemberService(projectMemberRepository, memberRepository, projectRepository,
                new ProjectService(projectRepository, textChannelRepository, memberRepository, projectMemberRepository));
        member = Member.of("name", "test@gmail.com", Oauth2.of(AuthProvider.KAKAO, "account"));
        project = Project.of("name", "description", true,
                "http://test.com", false, member);
    }

    @Test
    @DisplayName("프로젝트-회원 관계 추가")
    void createIfNone() {
        // given
        projectRepository.save(project);
        memberRepository.save(member);
        ProjectMemberCreateRequest request = new ProjectMemberCreateRequest(1L, 1L);

        // when
        ProjectMemberResponse response = projectMemberService.createIfNone(request);

        // then
        assertThat(response.name()).isEqualTo(member.getName());
        assertThat(response.email()).isEqualTo(member.getEmail());
    }

    @Test
    @DisplayName("프로젝트-회원 관계 삭제")
    void delete() {
        // given
        Long projectId = 1L;
        Long memberId = 1L;
        projectRepository.save(project);
        memberRepository.save(member);
        ProjectMemberId id = new ProjectMemberId(project, member);


        // when
        projectMemberRepository.save(ProjectMember.of(project, member));

        // then
        projectMemberRepository.findById(id).orElseThrow(EntityNotFoundException::new);


        // when
        projectMemberService.delete(projectId, memberId);

        // then
        if(projectMemberRepository.findById(id).isPresent()) {
            throw new EntityExistsException();
        }

        Project disabledProject = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);
        if(disabledProject.getEnabled()) {
            throw new ProjectNotDisabledException();
        }
    }

    @Test
    @DisplayName("프로젝트에 참여 중인 모든 회원 조회")
    void findByProjectId() {
        // given
        projectRepository.save(project);
        memberRepository.save(member);
        projectMemberRepository.save(ProjectMember.of(project, member));
        Long projectId = 1L;

        // when
        List<ProjectMemberResponse> findResponses = projectMemberService.findByProjectId(projectId);

        // then
        assertThat(findResponses.size()).isEqualTo(1);
        assertThat(findResponses.get(0).name()).isEqualTo("name");
        assertThat(findResponses.get(0).email()).isEqualTo("test@gmail.com");
    }

    @Test
    @DisplayName("회원이 참여 중인 모든 프로젝트 조회")
    void findByMemberId() {
        // given
        projectRepository.save(project);
        memberRepository.save(member);
        projectMemberRepository.save(ProjectMember.of(project, member));
        Long memberId = 1L;

        // when
        List<MemberProjectResponse> findResponses = projectMemberService.findByMemberId(memberId);

        // then
        assertThat(findResponses.size()).isEqualTo(1);
        assertThat(findResponses.get(0).name()).isEqualTo("name");
        assertThat(findResponses.get(0).description()).isEqualTo("description");
        assertThat(findResponses.get(0).enabled()).isEqualTo(Boolean.TRUE);
        assertThat(findResponses.get(0).invitationStatus()).isEqualTo(Boolean.FALSE);
    }
}
