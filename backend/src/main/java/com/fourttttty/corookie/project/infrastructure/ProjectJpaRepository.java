package com.fourttttty.corookie.project.infrastructure;

import com.fourttttty.corookie.project.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectJpaRepository extends JpaRepository<Project, Long> {
}
