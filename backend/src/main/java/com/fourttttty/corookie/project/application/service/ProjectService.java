package com.fourttttty.corookie.project.application.service;

import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.project.dto.request.ProjectCreateRequest;
import com.fourttttty.corookie.project.dto.request.ProjectUpdateRequest;
import com.fourttttty.corookie.project.dto.response.ProjectResponse;
import com.fourttttty.corookie.textchannel.application.service.TextChannelService;
import jakarta.persistence.EntityExistsException;
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

    public List<ProjectResponse> findAll(){
        return projectRepository.findAll().stream()
                .map(ProjectResponse::of)
                .toList();
    }

    public ProjectResponse findById(Long id) {
        return ProjectResponse.of(projectRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Transactional
    public ProjectResponse create(Project project) {
        Project createdProject = projectRepository.save(project);
        createInitialChannel(createdProject.getId());
        return ProjectResponse.of(project);
    }

    @Transactional
    public void createInitialChannel(Long projectId){
        textChannelService.createTextChannel("공지", projectId);
        textChannelService.createTextChannel("자유", projectId);
        textChannelService.createTextChannel("Front-End", projectId);
        textChannelService.createTextChannel("Back-End", projectId);
    }

    @Transactional
    public ProjectResponse modify(ProjectUpdateRequest request){
        Project project =  projectRepository.findById(request.id()).orElseThrow(EntityNotFoundException::new);
        project.update(request.name(), request.description());
        projectRepository.save(project);
        return ProjectResponse.of(project);
    }

    @Transactional
    public void deleteById(Long id){
        projectRepository.deleteById(id);
    }


}
