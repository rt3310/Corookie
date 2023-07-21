package com.fourttttty.corookie.project.application.service;

import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import com.fourttttty.corookie.project.domain.Project;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public List<Project> findAll(){
        return projectRepository.findAll();
    }

    public Project findById(Long id) {
        return projectRepository.findById(id).orElseThrow(EntityExistsException::new);
    }

    public void save(Project project) {
        projectRepository.save(project);
    }

    public void update(Project project){
        projectRepository.update(project);
    }

    public void deleteById(Long id){
        projectRepository.deleteById(id);
    }

}
