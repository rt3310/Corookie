package com.fourttttty.corookie.project.application.service;

import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.project.dto.request.ProjectCreateRequest;
import com.fourttttty.corookie.project.dto.request.ProjectUpdateRequest;
import com.fourttttty.corookie.project.dto.response.ProjectResponse;
import com.fourttttty.corookie.textchannel.application.service.TextChannelService;
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

    public List<ProjectResponse> findAll() {
        return projectRepository.findAll().stream()
                .map(ProjectResponse::of)
                .toList();
    }

    public ProjectResponse findById(Long projectId) {
        return ProjectResponse.of(projectRepository.findById(projectId).orElseThrow(EntityNotFoundException::new));
    }

    @Transactional
    public ProjectResponse create(ProjectCreateRequest projectCreateRequest) {
        Project project = projectRepository.save(projectCreateRequest.toEntity());
        createInitialTextChannel(project.getId());
        return ProjectResponse.of(project);
    }

    private void createInitialTextChannel(Long projectId) {
        textChannelService.create("공지", projectId);
        textChannelService.create("자유", projectId);
        textChannelService.create("Front-End", projectId);
        textChannelService.create("Back-End", projectId);
    }

    @Transactional
    public ProjectResponse modify(ProjectUpdateRequest request, Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(EntityNotFoundException::new);
        project.update(request.name(), request.description(), request.invLink(), request.invStatus());
        return ProjectResponse.of(project);
    }

    @Transactional
    public void deleteById(Long projectId) {
        projectRepository.findById(projectId).orElseThrow(EntityNotFoundException::new).delete();
    }

}
