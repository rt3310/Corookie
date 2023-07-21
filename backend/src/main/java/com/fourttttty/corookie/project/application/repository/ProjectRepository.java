package com.fourttttty.corookie.project.application.repository;


import com.fourttttty.corookie.project.domain.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    Optional<Project> findById(Long id);
    List<Project> findAll();
    void save(Project project);
    void update(Project project);
    void deleteById(Long id);
}
