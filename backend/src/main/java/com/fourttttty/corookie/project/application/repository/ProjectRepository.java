package com.fourttttty.corookie.project.application.repository;


import com.fourttttty.corookie.project.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    Optional<Project> findById(Long id);
    List<Project> findAll();
    Project save(Project project);
    void deleteById(Long id);
    Project modifyName(String name, String description, Long id);
}
