package com.fourttttty.corookie.plan.application.repository;


import com.fourttttty.corookie.plan.domain.Plan;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlanRepository{
    Optional<Plan> findById(Long id);

    @Query(value = "select * from plan where project_id = :projectId", nativeQuery = true)
    List<Plan> findByProjectId(Long projectId);

    Plan save(Plan plan);

}
