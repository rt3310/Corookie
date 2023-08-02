package com.fourttttty.corookie.texture.plan.application.repository;

import com.fourttttty.corookie.plan.application.repository.PlanCategoryRepository;
import com.fourttttty.corookie.plan.domain.PlanCategory;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Map;

public class FakePlanCategoryRepository implements PlanCategoryRepository {

    private long autoIncrementedId = 1L;
    private final Map<Long, PlanCategory> store = new HashMap<>();

    @Override
    public PlanCategory save(PlanCategory planCategory) {
        Optional<Entry<Long, PlanCategory>> first = store.entrySet().stream()
            .filter(entry -> entry.getValue().equals(planCategory.getContent()))
            .findFirst();
        if (first.isEmpty()) {
            store.put(autoIncrementedId, planCategory.of(autoIncrementedId,planCategory.getContent()));
            return store.get(autoIncrementedId++);
        }
        return first.get().getValue();
    }

    @Override
    public Optional<PlanCategory> findById(Long id) {
        return store.entrySet().stream()
            .filter(entry -> entry.getKey().equals(id))
            .map(entry -> store.get(entry.getKey()))
            .findAny();
    }

    @Override
    public Optional<PlanCategory> findByContent(String content) {
        return store.entrySet().stream()
            .filter(entry -> entry.getValue().getContent().equals(content))
            .map(entry -> store.get(entry.getKey()))
            .findAny();
    }
}
