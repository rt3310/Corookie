package com.fourttttty.corookie.project.infrastructure;

import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.project.domain.ProjectMember;
import com.fourttttty.corookie.project.domain.ProjectMemberId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectMemberJpaRepository extends JpaRepository<ProjectMember, ProjectMemberId>{
    List<ProjectMember> findByProject(Project project);
    Optional<ProjectMember> findByMemberAndProject(Project project, Member member);
    void deleteByMemberAndProject(Project project, Member member);
    long countByProject(Project project);
    Optional<Project> findProjectByProjectMemberId(ProjectMemberId id);
    Optional<Member> findMemberByProjectMemberId(ProjectMemberId id);
}
