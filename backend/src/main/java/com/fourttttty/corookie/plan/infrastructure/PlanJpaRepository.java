package com.fourttttty.corookie.plan.infrastructure;

import com.fourttttty.corookie.plan.domain.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanJpaRepository extends JpaRepository<Plan, Long> {

}
