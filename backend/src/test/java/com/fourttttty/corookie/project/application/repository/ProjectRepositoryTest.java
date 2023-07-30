package com.fourttttty.corookie.project.application.repository;


import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.texture.project.application.repository.FakeProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class ProjectRepositoryTest {
    ProjectRepository projectRepository;
    Member member;
    Project project;

    @BeforeEach
    void initObjects() {
        projectRepository = new FakeProjectRepository();
        member = new Member("name");
        project = Project.of("name",
                "description",
                true,
                "http://test.com",
                false,
                member);
    }

    @Test
    @DisplayName("프로젝트 저장 테스트")
    void saveProject() {
        // given
        Member member = new Member("Member");
        Project project = Project.of("New Project", "Project Description", Boolean.FALSE, "corookie.com/testlink", Boolean.FALSE, member);

        // when
        Project savedProject = projectRepository.save(project);

        //then
        assertThat(savedProject.getName()).isEqualTo(project.getName());
        assertThat(savedProject.getDescription()).isEqualTo(project.getDescription());
        assertThat(savedProject.getEnabled()).isEqualTo(project.getEnabled());
        assertThat(savedProject.getInvitationLink()).isEqualTo(project.getInvitationLink());
        assertThat(savedProject.getInvitationStatus()).isEqualTo(project.getInvitationStatus());
        assertThat(savedProject.getMember()).isEqualTo(project.getMember());
    }

    @Test
    @DisplayName("프로젝트 상세 조회")
    void findById() {
        // given
        Long projectId = 1L;
        Project project = Project.of("New Project", "Project Description", Boolean.FALSE, "corookie.com/testlink", Boolean.FALSE, member);
        projectRepository.save(project);

        // when
        Optional<Project> foundProject = projectRepository.findById(projectId);

        // then
        assertThat(foundProject).isPresent();
        assertThat(foundProject.get().getName()).isEqualTo(project.getName());
        assertThat(foundProject.get().getDescription()).isEqualTo(project.getDescription());
        assertThat(foundProject.get().getEnabled()).isEqualTo(project.getEnabled());
        assertThat(foundProject.get().getInvitationLink()).isEqualTo(project.getInvitationLink());
        assertThat(foundProject.get().getInvitationStatus()).isEqualTo(project.getInvitationStatus());
        assertThat(foundProject.get().getMember()).isEqualTo(project.getMember());
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
        List<Project> projects = projectRepository.findAll();

        // then
        for (int i = 0; i < projectList.size(); i++) {
            assertThat(projects.get(i).getName()).isEqualTo(projectList.get(i).getName());
            assertThat(projects.get(i).getDescription()).isEqualTo(projectList.get(i).getDescription());
            assertThat(projects.get(i).getEnabled()).isEqualTo(projectList.get(i).getEnabled());
            assertThat(projects.get(i).getInvitationLink()).isEqualTo(projectList.get(i).getInvitationLink());
            assertThat(projects.get(i).getInvitationStatus()).isEqualTo(projectList.get(i).getInvitationStatus());
        }
    }
}

