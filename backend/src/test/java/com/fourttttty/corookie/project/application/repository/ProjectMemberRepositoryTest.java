package com.fourttttty.corookie.project.application.repository;

import com.fourttttty.corookie.member.application.repository.MemberRepository;
import com.fourttttty.corookie.member.domain.AuthProvider;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.member.domain.Oauth2;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.project.domain.ProjectMember;
import com.fourttttty.corookie.project.domain.ProjectMemberId;
import com.fourttttty.corookie.texture.member.application.repository.FakeMemberRepository;
import com.fourttttty.corookie.texture.project.application.repository.FakeProjectMemberRepository;
import com.fourttttty.corookie.texture.project.application.repository.FakeProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectMemberRepositoryTest {

    ProjectMemberRepository projectMemberRepository;
    ProjectRepository projectRepository;
    MemberRepository memberRepository;
    private Member member;
    private Project project;
    private ProjectMember projectMember;

    @BeforeEach
    void initObjects() {
        projectRepository = new FakeProjectRepository();
        memberRepository = new FakeMemberRepository();
        projectMemberRepository = new FakeProjectMemberRepository(projectRepository, memberRepository);
        member = Member.of("name", "test@gmail.com", Oauth2.of(AuthProvider.KAKAO, "account"));
        project = Project.of("name",
                "description",
                Boolean.FALSE,
                "http://test.com",
                Boolean.FALSE,
                member);
    }

    @Test
    @DisplayName("회원으로 프로젝트 목록 조회")
    void findByMember() {
        // given
        projectMemberRepository.save(project, member);

        // when
        List<ProjectMember> list = projectMemberRepository.findByMember(member);
        Project foundProject = list.get(0).getId().getProject();

        // then
        assertThat(list.size()).isEqualTo(1);
        assertThat(foundProject.getName()).isEqualTo(project.getName());
        assertThat(foundProject.getDescription()).isEqualTo(project.getDescription());
        assertThat(foundProject.getEnabled()).isEqualTo(project.getEnabled());
        assertThat(foundProject.getInvitationLink()).isEqualTo(project.getInvitationLink());
        assertThat(foundProject.getInvitationStatus()).isEqualTo(project.getInvitationStatus());
        assertThat(foundProject.getMember()).isEqualTo(project.getMember());
    }

    List<ProjectMember> findByProject(Project project) {
        return null;
    }

    Optional<ProjectMember> findById(ProjectMemberId id) {
        return Optional.empty();
    }

    void deleteByProjectAndMember(ProjectMemberId id) {

    }

    long countByProject(Project project) {
        return 0;
    }

    void save(Project project, Member member) {

    }
}
