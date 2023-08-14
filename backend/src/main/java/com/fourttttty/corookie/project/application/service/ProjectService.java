package com.fourttttty.corookie.project.application.service;

import com.fourttttty.corookie.member.application.repository.MemberRepository;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.project.application.repository.ProjectMemberRepository;
import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.project.domain.ProjectMember;
import com.fourttttty.corookie.project.dto.request.ProjectCreateRequest;
import com.fourttttty.corookie.project.dto.request.ProjectUpdateRequest;
import com.fourttttty.corookie.project.dto.response.ProjectDetailResponse;
import com.fourttttty.corookie.project.dto.response.ProjectListResponse;
import com.fourttttty.corookie.textchannel.application.repository.TextChannelRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final TextChannelRepository textChannelRepository;
    private final MemberRepository memberRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final InvitationLinkGenerateService invitationLinkGenerateService;

    public List<ProjectListResponse> findByManagerId(Long managerId) {
        return projectRepository.findByManagerId(managerId).stream()
                .map(ProjectListResponse::from)
                .toList();
    }

    public List<ProjectListResponse> findByParticipantId(Long participantId) {
        return projectMemberRepository.findByMemberId(participantId).stream()
                .map(projectMember -> ProjectListResponse.from(projectMember.getId().getProject()))
                .toList();
    }

    public ProjectDetailResponse findById(Long projectId, Long managerId) {
        Project project = findEntityById(projectId);
        return ProjectDetailResponse.from(project, project.isManager(managerId));
    }

    public Project findEntityById(Long projectId) {
        return projectRepository.findById(projectId).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public ProjectDetailResponse create(ProjectCreateRequest projectCreateRequest, Long managerId) {
        Member member = memberRepository.findById(managerId).orElseThrow(EntityNotFoundException::new);
        Project project = projectRepository.save(projectCreateRequest.toEntity(member));

        registerMemberForProject(member, project);
        project.changeInvitationLink(invitationLinkGenerateService.generateInvitationLink(project.getId()));
        project.createDefaultTextChannels().forEach(textChannelRepository::save);
        return ProjectDetailResponse.from(project, project.isManager(managerId));
    }

    private void registerMemberForProject(Member member, Project project) {
        projectMemberRepository.save(ProjectMember.of(project, member));
    }

    @Transactional
    public ProjectDetailResponse modify(ProjectUpdateRequest request, Long projectId, Long managerId) {
        Project project = projectRepository.findById(projectId).orElseThrow(EntityNotFoundException::new);
        project.update(request.name(), request.description(), request.invitationLink(), request.invitationStatus());
        return ProjectDetailResponse.from(project, project.isManager(managerId));
    }

    @Transactional
    public void deleteById(Long projectId) {
        projectRepository.findById(projectId).orElseThrow(EntityNotFoundException::new).delete();
    }

}
