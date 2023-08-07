package com.fourttttty.corookie.texture.project.application.repository;

import com.fourttttty.corookie.member.application.repository.MemberRepository;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.project.application.repository.ProjectMemberRepository;
import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.project.domain.ProjectMember;
import com.fourttttty.corookie.project.domain.ProjectMemberId;
import jakarta.persistence.EntityNotFoundException;

import java.util.*;
import java.util.Map.Entry;

public class FakeProjectMemberRepository implements ProjectMemberRepository {

    private long autoIncrementId = 1L;
    private final Map<Id, ProjectMember> store = new HashMap<>();
    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;

    public FakeProjectMemberRepository(ProjectRepository projectRepository, MemberRepository memberRepository) {
        this.projectRepository = projectRepository;
        this.memberRepository = memberRepository;
    }

    class Id {
        private Long projectId;
        private Long memberId;

        private Id(Long projectId, Long memberId) {
            this.projectId = projectId;
            this.memberId = memberId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.projectId, this.memberId);
        }
    }

    @Override
    public List<ProjectMember> findByMemberId(Long memberId) {

        return store.entrySet().stream()
                .filter(entry -> entry.getKey().memberId
                .equals(memberId))
                .map(entry -> store.get(entry.getKey()))
                .toList();
    }

    @Override
    public List<ProjectMember> findByProjectId(Long projectId) {

        return store.entrySet().stream()
                .filter(entry -> entry.getKey().projectId.equals(projectId))
                .map(entry -> store.get(entry.getKey()))
                .toList();
    }

    @Override
    public Optional<ProjectMember> findById(ProjectMemberId id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public void deleteByProjectAndMember(ProjectMemberId id) {
        store.remove(id);
    }

    @Override
    public long countByProjectId(Long projectId) {
        return store.entrySet().stream()
                .filter(entry -> entry.getKey().projectId.equals(projectId))
                .count();
    }

    @Override
    public void save(ProjectMember projectMember) {
        Optional<Entry<Id, ProjectMember>> first = store.entrySet().stream()
                .filter(entry -> entry.getKey().equals(projectMember.getId()))
                .findFirst();
        if (first.isEmpty()) {
            store.put(new Id(projectId, memberId), projectMember);
        }
    }
}
