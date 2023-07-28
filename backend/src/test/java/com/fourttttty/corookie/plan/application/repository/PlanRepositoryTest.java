package com.fourttttty.corookie.plan.application.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.plan.domain.Plan;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.texture.plan.application.repository.FakePlanRepository;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlanRepositoryTest {

    PlanRepository planRepository;
    Project project;
    Member member;


    @BeforeEach
    void initObjects() {
        planRepository = new FakePlanRepository();
        member = new Member("name");
        project = Project.of("name",
            "description",
            true,
            "http://test.com",
            false,
            member);
    }

    @Test
    @DisplayName("Test PlanRepository's save")
    void save() {
        // given
        Plan plan = Plan.of("name",
            "testDescription",
            LocalDateTime.now(),
            LocalDateTime.now().minusDays(2),
            true,
            project);

        // when
        Plan savedPlan = planRepository.save(plan);

        // then
        assertThat(savedPlan.getId()).isEqualTo(plan.getId());
        assertThat(savedPlan.getPlanName()).isEqualTo(plan.getPlanName());
        assertThat(savedPlan.getDescription()).isEqualTo(plan.getDescription());
        assertThat(savedPlan.getPlanStart()).isEqualTo(plan.getPlanStart());
        assertThat(savedPlan.getPlanEnd()).isEqualTo(plan.getPlanEnd());
        assertThat(savedPlan.getEnabled()).isEqualTo(plan.getEnabled());
    }

    @Test
    @DisplayName("find Plan By Id")
    void findById() {
        // given
        Plan plan = Plan.of("name",
            "testDescription",
            LocalDateTime.now(),
            LocalDateTime.now().minusDays(2),
            true,
            project);
        planRepository.save(plan);

        // when
        Plan foundPlan = planRepository.findById(1L).orElseThrow(NoSuchElementException::new);

        // then
        assertThat(foundPlan.getId()).isEqualTo(plan.getId());
        assertThat(foundPlan.getPlanName()).isEqualTo(plan.getPlanName());
        assertThat(foundPlan.getDescription()).isEqualTo(plan.getDescription());
        assertThat(foundPlan.getPlanStart()).isEqualTo(plan.getPlanStart());
        assertThat(foundPlan.getPlanEnd()).isEqualTo(plan.getPlanEnd());
        assertThat(foundPlan.getEnabled()).isEqualTo(plan.getEnabled());
    }
}