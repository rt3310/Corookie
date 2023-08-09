package com.fourttttty.corookie.project.application.service;

import com.fourttttty.corookie.member.application.repository.MemberRepository;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.project.application.repository.ProjectMemberRepository;
import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.project.domain.ProjectMember;
import com.fourttttty.corookie.project.domain.ProjectMemberId;
import com.fourttttty.corookie.project.dto.request.ProjectMemberCreateRequest;
import com.fourttttty.corookie.project.dto.response.ProjectMemberResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectMemberService {

    private final ProjectMemberRepository projectMemberRepository;
    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;
    private final ProjectService projectService;

    @Transactional
    public ProjectMemberResponse createIfNone(ProjectMemberCreateRequest request) {
        Project project = projectRepository.findById(request.projectId()).orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findById(request.memberId()).orElseThrow(EntityNotFoundException::new);

        ProjectMember projectMember = projectMemberRepository.findById(new ProjectMemberId(project, member))
                .orElse(projectMemberRepository.save(ProjectMember.of(project, member)));

        return ProjectMemberResponse.from(projectMember);
    }

    @Transactional
    public void delete(Long projectId, Long memberId) {
        projectMemberRepository.deleteById(new ProjectMemberId(
                projectRepository.findById(projectId).orElseThrow(EntityNotFoundException::new),
                memberRepository.findById(memberId).orElseThrow(EntityNotFoundException::new)));

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

    public List<ProjectMemberResponse> findByProjectId(Long projectId) {
        return projectMemberRepository.findByProjectId(projectId).stream()
                .map(projectMember -> ProjectMemberResponse.from(
                        ProjectMember.of(projectMember.getId().getProject(), projectMember.getId().getMember())))
                .toList();
    }

    public List<ProjectMemberResponse> findByMemberId(Long memberId) {
        return projectMemberRepository.findByMemberId(memberId).stream()
                .map(projectMember -> ProjectMemberResponse.from(
                        ProjectMember.of(projectMember.getId().getProject(), projectMember.getId().getMember())))
                .toList();
    }

    public ProjectMemberResponse findById(ProjectMemberId projectMemberId) {
        return ProjectMemberResponse.from(projectMemberRepository.findById(projectMemberId)
                .orElseThrow(EntityNotFoundException::new));
    }
}
