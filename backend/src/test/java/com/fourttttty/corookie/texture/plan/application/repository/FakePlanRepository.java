package com.fourttttty.corookie.texture.plan.application.repository;

import com.fourttttty.corookie.plan.application.repository.PlanRepository;
import com.fourttttty.corookie.plan.domain.Plan;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class FakePlanRepository implements PlanRepository {

    Long autoIncrementId = 1L;
    private final Map<Long, Plan> store = new HashMap<>();

    @Override
    public List<Plan> findByDate(LocalDate date) {
        return store.values().stream()
                .filter(plan -> isEqualPlanStart(date, plan) || isEqualPlanEnd(date, plan))
                .toList();
    }

    private boolean isEqualPlanStart(LocalDate date, Plan plan) {
        return plan.getPlanStart().getYear() == date.getYear() && plan.getPlanStart().getMonthValue() == date.getMonthValue();
    }

    private boolean isEqualPlanEnd(LocalDate date, Plan plan) {
        return plan.getPlanEnd().getYear() == date.getYear() && plan.getPlanEnd().getMonthValue() == date.getMonthValue();
    }

    @Override
    public Optional<Plan> findById(Long id) {
        return Optional.ofNullable((store.get(id)));
    }

    @Override
    public Plan save(Plan plan) {
        store.put(autoIncrementId++, plan);
        return plan;
    }
}
