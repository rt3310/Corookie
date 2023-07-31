package com.fourttttty.corookie.texture.plan.application.repository;

import com.fourttttty.corookie.plan.application.repository.PlanRepository;
import com.fourttttty.corookie.plan.domain.Plan;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakePlanRepository implements PlanRepository {

    Long autoIncrementId = 1L;
    private final Map<Long, Plan> store = new HashMap<>();

    @Override
    public Optional<Plan> findById(Long id) {
        return store.entrySet().stream()
            .filter(entry -> entry.getKey().equals(id))
            .map(entry -> store.get(entry.getKey()))
            .findAny();
    }

    @Override
    public Plan save(Plan plan) {
        store.put(autoIncrementId, Plan.of(autoIncrementId,
            plan.getPlanName(),
            plan.getDescription(),
            plan.getPlanStart(),
            plan.getPlanEnd(),
            plan.getEnabled(),
            plan.getProject()));

        return store.get(autoIncrementId++);
    }
}
