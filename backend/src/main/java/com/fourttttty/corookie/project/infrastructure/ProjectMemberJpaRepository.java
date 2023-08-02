package com.fourttttty.corookie.project.infrastructure;

import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.project.domain.ProjectMember;
import com.fourttttty.corookie.project.domain.ProjectMemberId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectMemberJpaRepository extends JpaRepository<ProjectMember, ProjectMemberId> {
    List<ProjectMember> findByIdProject(Project project);
    Optional<ProjectMember> findById(ProjectMemberId id);
    long countByIdProject(Project project);
    void deleteById(ProjectMemberId id);

    Optional<Project> findProjectById(ProjectMemberId id);
    Optional<Member> findMemberById(ProjectMemberId id);
}
