package com.fourttttty.corookie.texture.plan.application.repository;

import com.fourttttty.corookie.plan.application.repository.CategoryInPlanRepository;
import com.fourttttty.corookie.plan.domain.CategoryInPlan;
import com.fourttttty.corookie.plan.domain.CategoryInPlanId;
import com.fourttttty.corookie.plan.domain.Plan;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class FakeCategoryInPlanRepository implements CategoryInPlanRepository {
    private final Map<CategoryInPlanId, CategoryInPlan> store = new HashMap<>();

    @Override
    public CategoryInPlan save(CategoryInPlan categoryInPlan) {
        return store.put(categoryInPlan.getId(), categoryInPlan);
    }

    @Override
    public void delete(CategoryInPlan categoryInPlan) {
        store.remove(categoryInPlan);
    }

    @Override
    public List<CategoryInPlan> findAllbyPlan(Plan plan) {
        return store.entrySet().stream()
            .filter(st -> st.getKey().getPlan().equals(plan))
            .map(st -> store.get(st.getKey()))
            .toList();
    }

    @Override
    public Boolean exists(CategoryInPlanId categoryInPlanId) {
        return store.containsKey(categoryInPlanId);
    }
}
