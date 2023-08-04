package com.fourttttty.corookie.plan.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "category_in_plan")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryInPlan {

    @EmbeddedId
    private CategoryInPlanId id;

    private CategoryInPlan(CategoryInPlanId categoryInPlanId) {
        this.id = categoryInPlanId;
    }

    public Boolean exists(List<Long> PlanCategoryIdList) {
        return PlanCategoryIdList.stream()
                .anyMatch(longId -> longId.equals(this.id.getPlanCategory().getId()));
    }

    public static CategoryInPlan of(Plan plan, PlanCategory planCategory) {
        return new CategoryInPlan(new CategoryInPlanId(plan, planCategory));
    }
}
