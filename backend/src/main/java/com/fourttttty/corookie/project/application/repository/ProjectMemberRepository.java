package com.fourttttty.corookie.project.application.repository;

import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.project.domain.ProjectMember;
import com.fourttttty.corookie.project.domain.ProjectMemberId;
import com.fourttttty.corookie.project.infrastructure.ProjectMemberJpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectMemberRepository {
    List<ProjectMember> findByMemberId(Long memberId);
    List<ProjectMember> findByProjectId(Long projectId);
    Optional<ProjectMember> findById(ProjectMemberId id);
    void deleteByProjectAndMember(ProjectMemberId id);
    long countByProjectId(Long projectId);
    void save(ProjectMember projectMember);
}
