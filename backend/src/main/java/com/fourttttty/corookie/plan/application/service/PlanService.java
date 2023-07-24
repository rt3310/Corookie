package com.fourttttty.corookie.plan.application.service;

import com.fourttttty.corookie.plan.application.repository.PlanRepository;
import com.fourttttty.corookie.plan.domain.Plan;
import com.fourttttty.corookie.plan.dto.request.PlanRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlanService {
    private final PlanRepository planRepository;

    public Plan findById(Long id) {
        return planRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Plan> findByProjcetId(Long projectId){
        return planRepository.findByProjectId(projectId);
    }

    @Transactional
    public Plan createPlan(PlanRequest planRequest){
        Plan newPlan = new Plan(planRequest.planName(),
                planRequest.description(),
                planRequest.planStart(),
                planRequest.planEnd());
        return planRepository.save(newPlan);
    }

}
