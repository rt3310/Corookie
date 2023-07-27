package com.fourttttty.corookie.project.application.service;

import com.fourttttty.corookie.member.application.service.MemberService;
import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.project.dto.request.ProjectCreateRequest;
import com.fourttttty.corookie.project.dto.request.ProjectUpdateRequest;
import com.fourttttty.corookie.project.dto.response.ProjectResponse;
import com.fourttttty.corookie.textchannel.application.service.TextChannelService;
import com.fourttttty.corookie.textchannel.domain.DefaultChannel;
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
    private final TextChannelService textChannelService;
    private final MemberService memberService;

    public List<ProjectResponse> findAll() {
        return projectRepository.findAll().stream()
                .map(ProjectResponse::from)
                .toList();
    }

    public ProjectResponse findById(Long projectId) {
        return ProjectResponse.from(findEntityById(projectId));
    }

    public Project findEntityById(Long projectId) {
        return projectRepository.findById(projectId).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public ProjectResponse create(ProjectCreateRequest projectCreateRequest, Long memberId) {
        Project project = projectRepository.save(projectCreateRequest.toEntity(memberService.findEntityById(memberId)));
        createInitialTextChannel(project.getId());
        return ProjectResponse.from(project);
    }

    private void createInitialTextChannel(Long projectId) {
        for (DefaultChannel defaultChannel : DefaultChannel.values()) {
            textChannelService.createDefaultChannel(defaultChannel.getChannelName(), projectId);
        }
    }

    @Transactional
    public ProjectResponse modify(ProjectUpdateRequest request, Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(EntityNotFoundException::new);
        project.update(request.name(), request.description(), request.invitationLink(), request.invitationStatus());
        return ProjectResponse.from(project);
    }

    @Transactional
    public void deleteById(Long projectId) {
        projectRepository.findById(projectId).orElseThrow(EntityNotFoundException::new).delete();
    }

}
