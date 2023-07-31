package com.fourttttty.corookie.project.application.service;

import com.fourttttty.corookie.member.application.repository.MemberRepository;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.project.application.repository.ProjectMemberRepository;
import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import com.fourttttty.corookie.project.domain.Project;
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
    public void deleteProjectMember(Long projectId, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(EntityNotFoundException::new);
        Project project = projectRepository.findById(projectId).orElseThrow(EntityNotFoundException::new);

        projectMemberRepository.deleteByMemberAndProject(project, member);

        deleteIfNotExistsMember(project);
    }

    private void deleteIfNotExistsMember(Project project) {
        if (notExistsProjectMember(project)) {
            projectService.deleteById(project.getId());
        }
    }

    private boolean notExistsProjectMember(Project project) {
        return projectMemberRepository.countByProject(project) <= 0;
    }
}
