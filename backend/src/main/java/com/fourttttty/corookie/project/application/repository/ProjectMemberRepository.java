package com.fourttttty.corookie.project.application.repository;

import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.project.domain.ProjectMember;
import com.fourttttty.corookie.project.domain.ProjectMemberId;
import com.fourttttty.corookie.project.infrastructure.ProjectMemberJpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectMemberRepository {
    List<ProjectMember> findByProject(Project project);
    Optional<ProjectMember> findByProjectAndMember(Project project, Member member);
    void deleteByProjectAndMember(Project project, Member member);
    long countByProject(Project project);
}
