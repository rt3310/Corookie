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
    public List<ProjectMember> findByMember(Member member) {
        return projectMemberJpaRepository.findByIdMember(member);
    }

    @Override
    public List<ProjectMember> findByProject(Project project) {
        return projectMemberJpaRepository.findByIdProject(project);
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
    public long countByProject(Project project) {
        return projectMemberJpaRepository.countByIdProject(project);
    }

    @Override
    public void save(Project project, Member member) {
        projectMemberJpaRepository.save(new ProjectMember(project, member));
    }
}

