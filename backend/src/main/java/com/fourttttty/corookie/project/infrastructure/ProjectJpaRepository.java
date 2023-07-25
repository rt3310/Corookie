package com.fourttttty.corookie.project.infrastructure;

import com.fourttttty.corookie.project.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProjectJpaRepository extends JpaRepository<Project, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Project p SET p.name = ?1, p.description = ?2 WHERE p.id = ?3")
    int modifyProjectName(String name, String description, Long id);

}
