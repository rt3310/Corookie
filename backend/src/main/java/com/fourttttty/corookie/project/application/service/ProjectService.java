package com.fourttttty.corookie.project.application.service;

import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.project.dto.request.ProjectCreateRequest;
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
        return projectRepository.findAll().stream()
                .map(ProjectResponse::of)
                .toList();
    }

    public ProjectResponse findById(Long id) {
        return ProjectResponse.of(projectRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Transactional
    public ProjectResponse create(Project project) {
        return ProjectResponse.of(projectRepository.save(project));
    }

    @Transactional
    public ProjectResponse modifyName(String name, Long id){
        return ProjectResponse.of(projectRepository.modifyName(name, id));
    }

    @Transactional
    public ProjectResponse modifyDescription(String name, Long id){
        return ProjectResponse.of(projectRepository.modifyDescription(name, id));
    }

    @Transactional
    public void deleteById(Long id){
        projectRepository.deleteById(id);
    }
}
