package com.fourttttty.corookie.project.application.service;

import com.fourttttty.corookie.global.exception.ProjectNotFoundException;
import com.fourttttty.corookie.member.application.repository.MemberRepository;
import com.fourttttty.corookie.member.application.service.MemberService;
import com.fourttttty.corookie.member.domain.AuthProvider;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.member.domain.Oauth2;
import com.fourttttty.corookie.project.application.repository.ProjectMemberRepository;
import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import com.fourttttty.corookie.project.dto.request.ProjectCreateRequest;
import com.fourttttty.corookie.project.dto.request.ProjectUpdateRequest;
import com.fourttttty.corookie.project.dto.response.ProjectResponse;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.textchannel.application.repository.TextChannelRepository;
import com.fourttttty.corookie.textchannel.application.service.TextChannelService;
import com.fourttttty.corookie.texture.member.application.repository.FakeMemberRepository;
import com.fourttttty.corookie.texture.project.application.repository.FakeProjectMemberRepository;
import com.fourttttty.corookie.texture.project.application.repository.FakeProjectRepository;
import com.fourttttty.corookie.texture.textchannel.application.repository.FakeTextChannelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

public class ProjectServiceTest {

    ProjectRepository projectRepository;
    MemberRepository memberRepository;
    TextChannelRepository textChannelRepository;
    ProjectMemberRepository projectMemberRepository;
    ProjectService projectService;
    private Member member;
    private Project project;

    @BeforeEach
    void initObjects() {
        projectRepository = new FakeProjectRepository();
        memberRepository = new FakeMemberRepository();
        textChannelRepository = new FakeTextChannelRepository();
        projectMemberRepository = new FakeProjectMemberRepository(projectRepository, memberRepository);
        member = Member.of("이름", "test@test.com", null);

        project = Project.of("name",
                "description",
                true,
                "http://test.com",
                false,
                member);
        projectRepository.save(project);

        projectService = new ProjectService(projectRepository, textChannelRepository, memberRepository, projectMemberRepository);
    }

    @Test
    @DisplayName("프로젝트 생성")
    void createProject() {
        // given
        ProjectCreateRequest request = new ProjectCreateRequest("name", "description", Boolean.FALSE);
        memberRepository.save(member);

        // when
        ProjectResponse response = projectService.create(request, 1L);

        // then
        assertThat(response.name()).isEqualTo(request.name());
        assertThat(response.description()).isEqualTo(request.description());
        assertThat(response.enabled()).isEqualTo(true);
        assertThat(response.invitationStatus()).isEqualTo(request.invitationStatus());
    }

    @Test
    @DisplayName("프로젝트 상세 조회")
    void findById() {
        // given
        Long projectId = 1L;

        // when
        ProjectResponse response = projectService.findById(projectId);

        // then
        assertThat(response.name()).isEqualTo(project.getName());
        assertThat(response.description()).isEqualTo(project.getDescription());
        assertThat(response.enabled()).isEqualTo(project.getEnabled());
        assertThat(response.invitationStatus()).isEqualTo(project.getInvitationStatus());
    }

    @Test
    @DisplayName("프로젝트 엔티티 조회")
    void findEntityById() {
        // given
        Long projectId = 1L;

        // when
        Project foundProject = projectRepository.findById(projectId).orElseThrow(ProjectNotFoundException::new);

        //then
        assertThat(foundProject.getName()).isEqualTo(project.getName());
        assertThat(foundProject.getDescription()).isEqualTo(project.getDescription());
        assertThat(foundProject.getEnabled()).isEqualTo(project.getEnabled());
        assertThat(foundProject.getInvitationStatus()).isEqualTo(project.getInvitationStatus());
    }

    @Test
    @DisplayName("프로젝트 목록 조회")
    void findAll() {
        // given
        memberRepository.save(member);
        projectRepository.save(project);
        projectMemberRepository.save(project, member);

        // when
        List<ProjectResponse> response = projectService.findAll(1L);

        // then
        assertThat(response.size()).isEqualTo(1);
        assertThat(response.get(0).name()).isEqualTo(project.getName());
        assertThat(response.get(0).description()).isEqualTo(project.getDescription());
        assertThat(response.get(0).enabled()).isEqualTo(project.getEnabled());
        assertThat(response.get(0).invitationLink()).isEqualTo(project.getInvitationLink());
        assertThat(response.get(0).invitationStatus()).isEqualTo(project.getInvitationStatus());
    }

    @Test
    @DisplayName("프로젝트 수정")
    void modify() {
        // given
        Long projectId = 1L;
        memberRepository.save(member);
        projectRepository.save(project);
        projectMemberRepository.save(project, member);

        ProjectUpdateRequest request = new ProjectUpdateRequest("modifiedName", "modifiedDesc",  "http://modified.com", true);

        // when
        ProjectResponse response = projectService.modify(request, projectId);

        // then
        assertThat(response.name()).isEqualTo("modifiedName");
        assertThat(response.description()).isEqualTo("modifiedDesc");
        assertThat(response.invitationLink()).isEqualTo("http://modified.com");
        assertThat(response.invitationStatus()).isEqualTo(true);
    }

    @Test
    @DisplayName("프로젝트 삭제")
    void delete() {
        // given
        Long projectId = 1L;
        projectRepository.save(project);

        //when
        projectService.deleteById(1L);

        //then
        assertThat(projectService.findById(1L).enabled()).isEqualTo(false);
    }
}
