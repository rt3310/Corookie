package com.fourttttty.corookie.plan.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

    private CategoryInPlan(Plan plan, PlanCategory planCategory) {
        this.id = new CategoryInPlanId(plan, planCategory);
    }

    public static CategoryInPlan of(Plan plan, PlanCategory planCategory) {
        return new CategoryInPlan(plan, planCategory);
    }
}
