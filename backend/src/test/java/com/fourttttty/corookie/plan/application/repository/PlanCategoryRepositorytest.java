package com.fourttttty.corookie.plan.application.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.fourttttty.corookie.config.audit.JpaAuditingConfig;
import com.fourttttty.corookie.plan.domain.PlanCategory;
import com.fourttttty.corookie.support.TestConfig;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import({JpaAuditingConfig.class, TestConfig.class})
public class PlanCategoryRepositorytest {
    @Autowired
    private TestEntityManager em;

    @Autowired
    private PlanCategoryRepository planCategoryRepository;

    @BeforeEach
    void setUp(){

    }

    @Test
    @DisplayName("일정 카테고리를 저장한다.")
    void save(){
        // given
        PlanCategory planCategory = PlanCategory.of(
            "testCategory1"
        );

        // when
        PlanCategory savedCategory = planCategoryRepository.save(planCategory);

        //then
        assertThat(savedCategory).isNotNull();
        assertThat(savedCategory.getContent()).isEqualTo(planCategory.getContent());
    }

    @Test
    @DisplayName("일정 카테고리Id를 통해 조회한다.")
    void findById(){
        // given
        PlanCategory planCategory = PlanCategory.of(
            "testCategory1"
        );
        planCategoryRepository.save(planCategory);

        // when
        Optional<PlanCategory> foundCategory = planCategoryRepository.findById(1L);

        //then
        assertThat(foundCategory).isNotEmpty();
        assertThat(foundCategory.get().getContent()).isEqualTo(planCategory.getContent());
        assertThat(foundCategory.get().getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("일정 카테고리 내용을 통해 조회한다.")
    void findByContent(){
        // given
        PlanCategory planCategory = PlanCategory.of(
            "testCategory1"
        );
        planCategoryRepository.save(planCategory);

        // when
        Optional<PlanCategory> foundCategory = planCategoryRepository.findByContent("testCategory1");

        //then
        assertThat(foundCategory).isNotEmpty();
        assertThat(foundCategory.get().getContent()).isEqualTo(planCategory.getContent());
    }
}
