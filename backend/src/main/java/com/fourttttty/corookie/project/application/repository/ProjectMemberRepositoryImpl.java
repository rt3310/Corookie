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
    public List<ProjectMember> findByProject(Project project) {
        return projectMemberJpaRepository.findByProject(project);
    }

    @Override
    public Optional<ProjectMember> findByMemberAndProject(Project project, Member member) {
        return projectMemberJpaRepository.findByMemberAndProject(project, member);
    }

    @Override
    public void deleteByMemberAndProject(Project project, Member member) {
        projectMemberJpaRepository.deleteByMemberAndProject(project, member);
    }

    @Override
    public long countByProject(Project project) {
        return projectMemberJpaRepository.countByProject(project);
    }

    @Override
    public Optional<Project> findProjectByProjectMemberId(ProjectMemberId id){
        return projectMemberJpaRepository.findProjectByProjectMemberId(id);
    };

    @Override
    public Optional<Member> findMemberByProjectMemberId(ProjectMemberId id){
        return projectMemberJpaRepository.findMemberByProjectMemberId(id);
    };
}

