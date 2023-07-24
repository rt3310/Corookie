package com.fourttttty.corookie.project.application.service;

import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.project.dto.response.ProjectResponse;
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

    public List<ProjectResponse> findAll(){
       // return projectRepository.findAll();
        return null;
    }

    public ProjectResponse findById(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return new ProjectResponse(project.getName(), project.getDescription(), project.getCreatedAt(), project.getUpdatedAt(), project.isEnabled(), project.getInvLink(), project.isInvStatus());
    }

    @Transactional
    public ProjectResponse create(Project project) {
        Project createdProject = projectRepository.save(project);
        return new ProjectResponse(createdProject.getName(), createdProject.getDescription(), createdProject.getCreatedAt(), createdProject.getUpdatedAt(), createdProject.isEnabled(), createdProject.getInvLink(), createdProject.isInvStatus());
    }

    @Transactional
    public void deleteById(Long id){
        projectRepository.deleteById(id);
    }
}
