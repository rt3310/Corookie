package com.fourttttty.corookie.project.infrastructure;

import com.fourttttty.corookie.project.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ProjectJpaRepository extends JpaRepository<Project, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Project p SET p.name = ?1 WHERE p.id = ?2")
    int modifyProjectName(String name, Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Project p SET p.description = ?1 WHERE p.id = ?2")
    int modifyProjectDescription(String description, Long id);

}
