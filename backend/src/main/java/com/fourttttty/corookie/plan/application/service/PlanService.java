package com.fourttttty.corookie.plan.application.service;

import com.fourttttty.corookie.plan.application.repository.PlanRepository;
import com.fourttttty.corookie.plan.domain.Plan;
import com.fourttttty.corookie.plan.dto.request.PlanCreateRequest;
import com.fourttttty.corookie.plan.dto.request.PlanUpdateRequest;
import com.fourttttty.corookie.plan.dto.response.PlanCategoryResponse;
import com.fourttttty.corookie.plan.dto.response.PlanResponse;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlanService {

    private final PlanRepository planRepository;
    private final PlanCategoryService planCategoryService;

    public PlanResponse findById(Long id) {
        Plan plan = planRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return PlanResponse.of(plan, planCategoryService.findAllByPlan(plan));
    }

    @Transactional
    public PlanResponse createPlan(PlanCreateRequest planCreateRequest) {
        Plan plan = planRepository.save(
                Plan.of(planCreateRequest.planName(),
                        planCreateRequest.description(),
                        planCreateRequest.planStart(),
                        planCreateRequest.planEnd()));

        return PlanResponse.of(plan, planCreateRequest.categories().stream()
                .map(planCategoryCreateRequest -> planCategoryService.create(plan ,planCategoryCreateRequest))
                .toList());
    }

    @Transactional
    public void modifyPlan(Long planId, PlanUpdateRequest planUpdateRequest) {
        planRepository.findById(planId).orElseThrow(EntityNotFoundException::new)
                .update(planUpdateRequest.planName(), planUpdateRequest.description(),
                        planUpdateRequest.planStart(), planUpdateRequest.planEnd());
    }

    @Transactional
    public void deletePlan(Long planId) {
        planRepository.findById(planId).orElseThrow(EntityNotFoundException::new).delete();
    }
}
