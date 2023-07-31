package com.fourttttty.corookie.texture.plan.application.repository;

import com.fourttttty.corookie.plan.application.repository.CategoryInPlanRepository;
import com.fourttttty.corookie.plan.domain.CategoryInPlan;
import com.fourttttty.corookie.plan.domain.CategoryInPlanId;
import com.fourttttty.corookie.plan.domain.Plan;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class FakeCategoryInPlanRepository implements CategoryInPlanRepository {
    private final Map<CategoryInPlanId, CategoryInPlan> store = new HashMap<>();

    @Override
    public CategoryInPlan save(CategoryInPlan categoryInPlan) {
        Optional<Entry<CategoryInPlanId,CategoryInPlan>> first = store.entrySet().stream()
            .filter(entry->(entry.getValue().getId().getPlan().equals(categoryInPlan.getId().getPlan()))
            &&(entry.getValue().getId().getPlanCategory().equals(categoryInPlan.getId().getPlanCategory())))
            .findFirst();
        if(first.isEmpty()){
            return store.put(categoryInPlan.getId(),categoryInPlan);
        }
        return first.get().getValue();
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
