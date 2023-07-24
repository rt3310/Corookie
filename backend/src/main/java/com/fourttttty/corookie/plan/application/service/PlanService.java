package com.fourttttty.corookie.plan.application.service;

import com.fourttttty.corookie.plan.application.repository.PlanRepository;
import com.fourttttty.corookie.plan.domain.Plan;
import com.fourttttty.corookie.plan.dto.request.PlanRequest;
import com.fourttttty.corookie.plan.dto.response.PlanResponse;

import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlanService {
    private final PlanRepository planRepository;

    public PlanResponse findById(Long id) {
        return PlanResponse.of(planRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    @Transactional
    public PlanResponse createPlan(PlanRequest planRequest) {
        return PlanResponse.of(Plan.builder()
                .planName(planRequest.planName())
                .description(planRequest.description())
                .planStart(planRequest.planStart())
                .planEnd(planRequest.planEnd())
                .build());
    }

    @Transactional
    public void modifyPlan(Long planId, PlanRequest planRequest) {
        Plan plan = planRepository.findById(planId).orElseThrow(EntityNotFoundException::new);
        plan.update(planRequest.planName(), planRequest.description(), planRequest.planStart(), planRequest.planEnd());
    }

    @Transactional
    public void deletePlan(Long planId) {
        Plan plan = planRepository.findById(planId).orElseThrow(EntityNotFoundException::new);
        plan.delete();
    }
}
