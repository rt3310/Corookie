package com.fourttttty.corookie.texture.plan.application.repository;

import com.fourttttty.corookie.plan.application.repository.CategoryInPlanRepository;
import com.fourttttty.corookie.plan.application.repository.PlanCategoryRepository;
import com.fourttttty.corookie.plan.domain.PlanCategory;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Map;

public class FakePlanCategoryRepository implements PlanCategoryRepository {
    private CategoryInPlanRepository categoryInPlanRepository;
    public FakePlanCategoryRepository(CategoryInPlanRepository categoryInPlanRepository){
        this.categoryInPlanRepository = categoryInPlanRepository;
    }
    private long autoIncrementdId = 1L;
    private final Map<Long, PlanCategory> store = new HashMap<>();

    @Override
    public PlanCategory save(PlanCategory planCategory) {
        store.put(autoIncrementdId++, planCategory);
        return planCategory;
    }

    @Override
    public Optional<PlanCategory> findById(Long id) {
        return Optional.ofNullable((store.get(id)));
    }

    @Override
    public Optional<PlanCategory> findByContent(String content) {
        return store.entrySet().stream()
            .filter(entry -> entry.getValue().getContent().equals(content))
            .map(entry -> store.get(entry.getKey()))
            .findAny();
    }
}
