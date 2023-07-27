package com.fourttttty.corookie.project.application.repository;

import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.texture.project.application.repository.FakeProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    void saveProject(){
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
}

