package com.fourttttty.corookie.project.application.repository;

import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.project.domain.ProjectMember;
import com.fourttttty.corookie.project.domain.ProjectMemberId;
import com.fourttttty.corookie.project.infrastructure.ProjectMemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProjectMemberRepositoryImpl implements ProjectMemberRepository {
    private final ProjectMemberJpaRepository projectMemberJpaRepository;

    @Override
    public List<ProjectMember> findByMemberId(Long memberId) {
        return projectMemberJpaRepository.findAllById_MemberId(memberId);
    }

    @Override
    public List<ProjectMember> findByProjectId(Long projectId) {
        return projectMemberJpaRepository.findAllById_ProjectId(projectId);
    }

    @Override
    public Optional<ProjectMember> findById(ProjectMemberId id) {
        return projectMemberJpaRepository.findById(id);
    }

    @Override
    public void deleteByProjectAndMember(ProjectMemberId id) {
        projectMemberJpaRepository.deleteById(id);
    }

    @Override
    public long countByProjectId(Long projectId) {
        return projectMemberJpaRepository.countById_ProjectId(projectId);
    }

    @Override
    public void save(ProjectMember projectMember) {
        projectMemberJpaRepository.save(projectMember);
    }
}

