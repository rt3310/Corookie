package com.fourttttty.corookie.project.application.repository;

import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.project.infrastructure.ProjectJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProjectRepositoryImpl implements ProjectRepository {
    private final ProjectJpaRepository projectJpaRepository;

    @Override
    public Optional<Project> findById(Long id) {
        return projectJpaRepository.findById(id);
    }

    @Override
    public List<Project> findAll() {
        return projectJpaRepository.findAll();
    }

    @Override
    public Project save(Project project){
        return projectJpaRepository.save(project);
    }

    @Override
    public void deleteById(Long id) {
        projectJpaRepository.deleteById(id);
    }

}

