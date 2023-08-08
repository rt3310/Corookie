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

import java.util.ArrayList;
import java.util.List;

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
                Boolean.TRUE,
                "http://test.com",
                Boolean.FALSE,
                member);
    }

    @Test
    @DisplayName("회원으로 프로젝트 목록 조회")
    void findByMemberId() {
        // given
        memberRepository.save(member);
        Project project2 = Project.of("name2",
                "description2",
                Boolean.TRUE,
                "http://test2.com",
                Boolean.FALSE,
                member);

        List<Project> firstList = new ArrayList<>();
        firstList.add(project);
        firstList.add(project2);

        projectMemberRepository.save(ProjectMember.of(project, member));
        projectMemberRepository.save(ProjectMember.of(project2, member));
        Long memberId = 1L;

        // when
        List<ProjectMember> secondList = projectMemberRepository.findByMemberId(memberId);

        // then
        assertThat(firstList.size()).isEqualTo(secondList.size());
        for (int i = 0; i < secondList.size(); i++) {
            Project foundProject = secondList.get(i).getId().getProject();
            assertThat(foundProject.getName()).isEqualTo(firstList.get(i).getName());
            assertThat(foundProject.getDescription()).isEqualTo(firstList.get(i).getDescription());
            assertThat(foundProject.getEnabled()).isEqualTo(firstList.get(i).getEnabled());
            assertThat(foundProject.getInvitationLink()).isEqualTo(firstList.get(i).getInvitationLink());
            assertThat(foundProject.getInvitationStatus()).isEqualTo(firstList.get(i).getInvitationStatus());
            assertThat(foundProject.getMember()).isEqualTo(firstList.get(i).getMember());
        }
    }

    @Test
    @DisplayName("프로젝트의 회원 목록 조회")
    void findByProjectId() {
        // given
        projectRepository.save(project);

        Member member2 = Member.of("name2", "test2@gmail.com", Oauth2.of(AuthProvider.KAKAO, "account"));
        memberRepository.save(member);
        memberRepository.save(member2);

        List<Member> firstList = new ArrayList<>();
        firstList.add(member);
        firstList.add(member2);

        projectMemberRepository.save(ProjectMember.of(project, member));
        projectMemberRepository.save(ProjectMember.of(project, member2));
        Long projectId = 1L;

        // when
        List<ProjectMember> secondList = projectMemberRepository.findByProjectId(projectId);

        // then
        assertThat(firstList.size()).isEqualTo(secondList.size());
        for (int i = 0; i < secondList.size(); i++) {
            Member foundMember = secondList.get(i).getId().getMember();
            assertThat(foundMember.getName()).isEqualTo(firstList.get(i).getName());
            assertThat(foundMember.getEmail()).isEqualTo(firstList.get(i).getEmail());
        }
    }

    @Test
    @DisplayName("프로젝트에서 회원 삭제")
    void deleteByProjectAndMember() {
        // given
        projectRepository.save(project);
        memberRepository.save(member);
        ProjectMember projectMember = ProjectMember.of(project, member);
        projectMemberRepository.save(projectMember);
        Long memberId = 1L;
        Long projectId = 1L;
        ProjectMemberId id = new ProjectMemberId(project, member);

        //when
        projectMemberRepository.deleteById(id);
        List<ProjectMember> list1 = projectMemberRepository.findByMemberId(1L);
        List<ProjectMember> list2 = projectMemberRepository.findByProjectId(1L);

        //then
        assertThat(list1.size() == 0);
        assertThat(list2.size() == 0);
    }

    @Test
    @DisplayName("프로젝트에 참여 중인 회원 수")
    void countByProject() {
        // given
        projectRepository.save(project);
        memberRepository.save(member);

        Member member2 = Member.of("name2", "test2@gmail.com", Oauth2.of(AuthProvider.KAKAO, "account"));
        memberRepository.save(member2);

        List<Member> list = new ArrayList<>();
        list.add(member);
        list.add(member2);

        projectMemberRepository.save(ProjectMember.of(project, member));
        projectMemberRepository.save(ProjectMember.of(project, member2));
        Long projectId = 1L;

        // when
        Long count = projectMemberRepository.countByProjectId(projectId);

        // then
        assertThat(count).isEqualTo(list.size());
    }

    @Test
    @DisplayName("프로젝트에 회원 등록")
    void save() {
        // given
        projectRepository.save(project);
        memberRepository.save(member);
        ProjectMember projectMember = ProjectMember.of(project, member);
        projectMemberRepository.save(projectMember);
        Long memberId = 1L;
        Long projectId = 1L;

        // when
        ProjectMember foundProjectMember1 = projectMemberRepository.findByMemberId(memberId).get(0);
        ProjectMember foundProjectMember2 = projectMemberRepository.findByProjectId(projectId).get(0);

        // then
        assertThat(foundProjectMember1.equals(foundProjectMember2));
        assertThat(foundProjectMember1.getId().getProject().equals(project));
        assertThat(foundProjectMember1.getId().getMember().equals(member));
    }
}
