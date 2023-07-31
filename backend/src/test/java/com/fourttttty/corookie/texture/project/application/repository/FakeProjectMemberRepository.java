package com.fourttttty.corookie.texture.project.application.repository;

import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.project.application.repository.ProjectMemberRepository;
import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.project.domain.ProjectMember;
import com.fourttttty.corookie.project.domain.ProjectMemberId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FakeProjectMemberRepository implements ProjectMemberRepository {

    private long autoIncrementId = 1L;
    private final Map<ProjectMemberId, ProjectMember> store = new HashMap<>();

    @Override
    public List<ProjectMember> findByProject(Project project) {
        return store.values().stream().filter(pm -> pm.getId().equals(project))
                .toList();
    }

    @Override
    public Optional<ProjectMember> findByMemberAndProject(Project project, Member member) {
        return Optional.ofNullable(store.get(new ProjectMemberId(project, member)));
    }

    @Override
    public void deleteByMemberAndProject(Project project, Member member) {

    }

    @Override
    public long countByProject(Project project) {
        return 0;
    }
}
