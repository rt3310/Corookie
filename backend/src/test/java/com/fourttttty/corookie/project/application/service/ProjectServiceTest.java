package com.fourttttty.corookie.project.application.service;

import com.fourttttty.corookie.member.application.repository.MemberRepository;
import com.fourttttty.corookie.member.application.service.MemberService;
import com.fourttttty.corookie.member.domain.Member;
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
    MemberService memberService;
    private Member member;
    private Project project;

    @BeforeEach
    void initObjects() {
        projectRepository = new FakeProjectRepository();
        memberRepository = new FakeMemberRepository();
        textChannelRepository = new FakeTextChannelRepository();
        member = new Member("name");
        project = Project.of("name", "description", true,
                "http://test.com", false, member);
        memberService = new MemberService(memberRepository);
        projectService = new ProjectService(projectRepository, new TextChannelService(textChannelRepository, projectRepository), memberService);
    }

    //To-Do : FakeRepository 이용할 때 Project Entity에 id값 생성해서, TextChannelService 정상 동작하게 하기
    @Test
    @DisplayName("프로젝트 생성")
    void createProject() {
        // given
        ProjectCreateRequest request = new ProjectCreateRequest("name", "description", Boolean.FALSE);
        memberRepository.save(member);

        // when
        ProjectResponse response = projectService.create(request, 1L);

        // then
        assertThat(response.name()).isEqualTo(project.getName());
        assertThat(response.description()).isEqualTo(project.getDescription());
        assertThat(response.enabled()).isEqualTo(project.getEnabled());
        assertThat(response.invitationLink()).isEqualTo(project.getInvitationLink());
        assertThat(response.invitationStatus()).isEqualTo(project.getInvitationStatus());
    }

    @Test
    @DisplayName("프로젝트 상세 조회")
    void findById() {
        // given
        Long projectId = 1L;
        Project project = Project.of("New Project", "Project Description", Boolean.FALSE, "corookie.com/testlink", Boolean.FALSE, member);
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
        member = new Member("Test Member");
        List<Project> projectList = new ArrayList<>();
        Project project1 = Project.of("Project 1", "Project 1 Description", Boolean.FALSE, "corookie.com/testlink1", Boolean.FALSE, member);
        Project project2 = Project.of("Project 2", "Project 2 Description", Boolean.TRUE, "corookie.com/testlink2", Boolean.FALSE, member);

        projectList.add(project1);
        projectList.add(project2);

        projectRepository.save(project1);
        projectRepository.save(project2);

        // when
        List<ProjectResponse> response = projectService.findAll();

        // then
        for (int i = 0; i < projectList.size(); i++) {
            assertThat(response.get(i).name()).isEqualTo(projectList.get(i).getName());
            assertThat(response.get(i).description()).isEqualTo(projectList.get(i).getDescription());
            assertThat(response.get(i).enabled()).isEqualTo(projectList.get(i).getEnabled());
            assertThat(response.get(i).invitationLink()).isEqualTo(projectList.get(i).getInvitationLink());
            assertThat(response.get(i).invitationStatus()).isEqualTo(projectList.get(i).getInvitationStatus());
        }
    }
}
