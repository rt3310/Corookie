package com.fourttttty.corookie.project.application.repository;


import com.fourttttty.corookie.project.domain.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository {
    Optional<Project> findById(Long id);
    Optional<Project> findByInvitationLink(String invitationLink);
    List<Project> findAll();
    Project save(Project project);
}
