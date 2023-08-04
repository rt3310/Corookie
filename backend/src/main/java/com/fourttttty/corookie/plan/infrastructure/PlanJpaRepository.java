package com.fourttttty.corookie.plan.infrastructure;

import com.fourttttty.corookie.plan.domain.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlanJpaRepository extends JpaRepository<Plan, Long> {

    @Query("select p from Plan p where (year(p.planStart) = :year and month(p.planStart) = :month) or (year(p.planEnd) = :year and month(p.planEnd) = :month)")
    List<Plan> findAllByDate(@Param("year") Integer year, @Param("month") Integer month);

}
