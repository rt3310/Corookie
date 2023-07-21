package com.fourttttty.corookie.project.application.repository;

import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.project.infrastructure.ProjectJpaRepository;
import jakarta.transaction.Transactional;
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
    @Transactional
    public void save(Project project){
        projectJpaRepository.save(project);
    }

    @Override
    @Transactional
    public void update(Project project){
        projectJpaRepository.save(project);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        projectJpaRepository.deleteById(id);
    }
}

