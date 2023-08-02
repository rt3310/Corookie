package com.fourttttty.corookie.project.application.service;

import com.fourttttty.corookie.member.application.repository.MemberRepository;
import com.fourttttty.corookie.member.application.service.MemberService;
import com.fourttttty.corookie.member.domain.AuthProvider;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.member.domain.Oauth2;
import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import com.fourttttty.corookie.project.dto.request.ProjectCreateRequest;
import com.fourttttty.corookie.project.dto.response.ProjectResponse;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.textchannel.application.repository.TextChannelRepository;
import com.fourttttty.corookie.textchannel.application.service.TextChannelService;
import com.fourttttty.corookie.texture.member.application.repository.FakeMemberRepository;
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
    ProjectService projectService;
    private Member member;

    @BeforeEach
    void initObjects() {
        projectRepository = new FakeProjectRepository();
        memberRepository = new FakeMemberRepository();
        textChannelRepository = new FakeTextChannelRepository();
        member = Member.of("이름", "test@test.com", null);
        projectService = new ProjectService(projectRepository, textChannelRepository, memberRepository);
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
        Project project = Project.of("name",
                "description",
                true,
                "http://test.com",
                false,
                member);
        projectRepository.save(project);

        // when
        ProjectResponse response = projectService.findById(projectId);

        // then
        assertThat(response.name()).isEqualTo(project.getName());
        assertThat(response.description()).isEqualTo(project.getDescription());
        assertThat(response.enabled()).isEqualTo(project.getEnabled());
        assertThat(response.invitationStatus()).isEqualTo(project.getInvitationStatus());
    }

    @Test
    @DisplayName("프로젝트 목록 조회")
    void findAll() {
        // given
        Project project = Project.of("name",
                "description",
                true,
                "http://test.com",
                false,
                member);
        projectRepository.save(project);

        // when
        List<ProjectResponse> response = projectService.findAll();

        // then
        assertThat(response.size()).isEqualTo(1);
        assertThat(response.get(0).name()).isEqualTo(project.getName());
        assertThat(response.get(0).description()).isEqualTo(project.getDescription());
        assertThat(response.get(0).enabled()).isEqualTo(project.getEnabled());
        assertThat(response.get(0).invitationLink()).isEqualTo(project.getInvitationLink());
        assertThat(response.get(0).invitationStatus()).isEqualTo(project.getInvitationStatus());
    }
}
