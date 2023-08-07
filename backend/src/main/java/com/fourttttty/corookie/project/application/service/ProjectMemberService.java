package com.fourttttty.corookie.project.application.service;

import com.fourttttty.corookie.member.application.repository.MemberRepository;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.project.application.repository.ProjectMemberRepository;
import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.project.domain.ProjectMember;
import com.fourttttty.corookie.project.domain.ProjectMemberId;
import com.fourttttty.corookie.project.dto.request.ProjectMemberCreateRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectMemberService {

    private final ProjectMemberRepository projectMemberRepository;
    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;
    private final ProjectService projectService;

    @Transactional
    public void create(ProjectMemberCreateRequest request) {
        Project project = projectRepository
                .findById(request.projectId())
                .orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findById(request.memberId()).orElseThrow(EntityNotFoundException::new);
        projectMemberRepository.save(ProjectMember.of(project, member));
    }

    @Transactional
    public void deleteProjectMember(Long projectId, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(EntityNotFoundException::new);
        Project project = projectRepository.findById(projectId).orElseThrow(EntityNotFoundException::new);

        projectMemberRepository.deleteByProjectAndMember(new ProjectMemberId(project, member));

        deleteIfNotExistsMember(projectId);
    }

    private void deleteIfNotExistsMember(Long projectId) {
        if (notExistsProjectMember(projectId)) {
            projectService.deleteById(projectId);
        }
    }

    private boolean notExistsProjectMember(Long projectId) {
        return projectMemberRepository.countByProjectId(projectId) <= 0;
    }
}
