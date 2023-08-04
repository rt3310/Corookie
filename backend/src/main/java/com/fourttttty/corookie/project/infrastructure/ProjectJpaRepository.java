package com.fourttttty.corookie.project.infrastructure;

import com.fourttttty.corookie.project.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectJpaRepository extends JpaRepository<Project, Long> {
    Optional<Project> findByInvitationLink(String InvitationLink);
}
