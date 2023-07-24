package com.fourttttty.corookie.plan.application.service;

import com.fourttttty.corookie.plan.application.repository.PlanRepository;
import com.fourttttty.corookie.plan.domain.Plan;
import com.fourttttty.corookie.plan.dto.request.PlanRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Getter
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

   @Transactional
    public void modifyPlan(Long planId, PlanRequest planRequest){
        Plan plan = planRepository.findById(planId).orElseThrow(EntityNotFoundException::new);
        plan.update(planRequest.planName(),
                planRequest.description(),
                planRequest.planStart(),
                planRequest.planEnd());

   }

   @Transactional
    public void deletePlan(Long planId){
       Plan plan = planRepository.findById(planId).orElseThrow(EntityNotFoundException::new);
       plan.delete();
   }
}
