package com.fourttttty.corookie.plan.application.repository;


import com.fourttttty.corookie.plan.domain.Plan;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlanRepositoryImpl implements PlanRepository{

    private final PlanRepository planRepository;
    @Override
    public Optional<Plan> findById(Long id) { return planRepository.findById(id); }
}
