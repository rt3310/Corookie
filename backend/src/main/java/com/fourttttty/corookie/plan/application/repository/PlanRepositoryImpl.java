package com.fourttttty.corookie.plan.application.repository;


import com.fourttttty.corookie.plan.domain.Plan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlanRepositoryImpl implements PlanRepository{

    private final PlanRepository planRepository;

    @Override
    public Optional<Plan> findById(Long id) {
        return planRepository.findById(id);
    }

    @Override
    public List<Plan> findByProjectId(Long projectId) {
        return planRepository.findByProjectId(projectId);
    }

    @Override
    public Plan save(Plan plan) {
        return planRepository.save(plan);
    }


}
