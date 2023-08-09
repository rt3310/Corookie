package com.fourttttty.corookie.project.application.service;

import com.fourttttty.corookie.member.application.repository.MemberRepository;
import com.fourttttty.corookie.project.application.repository.ProjectMemberRepository;
import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import com.fourttttty.corookie.project.domain.Project;
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

    public List<ProjectListResponse> findByMemberId(Long memberId) {
        return projectMemberRepository.findByMemberId(memberId).stream()
                .map(projectMember -> ProjectListResponse.from(projectMember.getId().getProject()))
                .toList();
    }

    public ProjectDetailResponse findById(Long projectId) {
        return ProjectDetailResponse.from(findEntityById(projectId));
    }

    public Project findEntityById(Long projectId) {
        return projectRepository.findById(projectId).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public ProjectDetailResponse create(ProjectCreateRequest projectCreateRequest, Long memberId) {
        Project project = projectRepository.save(projectCreateRequest.toEntity(
                memberRepository.findById(memberId).orElseThrow(EntityNotFoundException::new)));

        project.changeInvitationLink(invitationLinkGenerateService.generateInvitationLink((long) project.hashCode()));
        project.createDefaultTextChannels().forEach(textChannelRepository::save);
        return ProjectDetailResponse.from(project);
    }

    @Transactional
    public ProjectDetailResponse modify(ProjectUpdateRequest request, Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(EntityNotFoundException::new);
        project.update(request.name(), request.description(), request.invitationLink(), request.invitationStatus());
        return ProjectDetailResponse.from(project);
    }

    @Transactional
    public void deleteById(Long projectId) {
        projectRepository.findById(projectId).orElseThrow(EntityNotFoundException::new).delete();
    }

}
