package com.fourttttty.corookie.texture.plan.application.repository;

import com.fourttttty.corookie.global.exception.PlanNotFoundException;
import com.fourttttty.corookie.plan.application.repository.CategoryInPlanRepository;
import com.fourttttty.corookie.plan.application.repository.PlanRepository;
import com.fourttttty.corookie.plan.domain.CategoryInPlan;
import com.fourttttty.corookie.plan.domain.CategoryInPlanId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

public class FakeCategoryInPlanRepository implements CategoryInPlanRepository {
    private final Map<Id, CategoryInPlan> store = new HashMap<>();
    private final PlanRepository planRepository;

    public FakeCategoryInPlanRepository(PlanRepository planRepository){
        this.planRepository = planRepository;
    }

    class Id{
        private Long categoryId;
        private Long planId;

        private Id(CategoryInPlan categoryInPlan){
            this.planId = categoryInPlan.getId().getPlan().getId();
            this.categoryId = categoryInPlan.getId().getPlanCategory().getId();
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.categoryId,this.planId);
        }
    }

    @Override
    public CategoryInPlan save(CategoryInPlan categoryInPlan) {
        Optional<Entry<Id,CategoryInPlan>> first = store.entrySet().stream()
            .filter(entry->entry.getKey().equals(categoryInPlan.getId()))
            .findFirst();
        if(first.isEmpty()){
            store.put(new Id(categoryInPlan),categoryInPlan);
            return store.get(categoryInPlan.getId());
        }
        return first.get().getValue();
    }

    @Override
    public void delete(CategoryInPlan categoryInPlan) {
        store.remove(categoryInPlan.getId());
    }

    @Override
    public List<CategoryInPlan> findByPlanId(Long planId) {
        return store.entrySet().stream()
            .filter(entry->entry.getValue().getId().getPlan()
                .equals(planRepository.findById(planId).orElseThrow(PlanNotFoundException::new)))
            .map(entry->store.get(entry.getKey()))
            .toList();
    }


    @Override
    public Boolean exists(CategoryInPlanId categoryInPlanId) {
        return store.containsKey(categoryInPlanId);
    }
}
