package com.fourttttty.corookie.plan.application.repository;

import com.fourttttty.corookie.plan.domain.Plan;

import java.util.Optional;

public interface PlanRepository{
    Optional<Plan> findById(Long id);

    Plan save(Plan plan);

}
