package com.fourttttty.corookie.support;

import com.fourttttty.corookie.issue.application.repository.IssueRepository;
import com.fourttttty.corookie.issue.application.repository.IssueRepositoryImpl;
import com.fourttttty.corookie.issue.infrastructure.IssueJpaRepository;
import com.fourttttty.corookie.member.application.repository.MemberRepository;
import com.fourttttty.corookie.member.application.repository.MemberRepositoryImpl;
import com.fourttttty.corookie.member.infrastructure.MemberJpaRepository;
import com.fourttttty.corookie.plan.application.repository.PlanCategoryRepository;
import com.fourttttty.corookie.plan.application.repository.PlanCategoryRepositoryImpl;
import com.fourttttty.corookie.plan.application.repository.PlanRepository;
import com.fourttttty.corookie.plan.application.repository.PlanRepositoryImpl;
import com.fourttttty.corookie.plan.infrastructure.PlanCategoryJpaRepository;
import com.fourttttty.corookie.plan.infrastructure.PlanJpaRepository;
import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import com.fourttttty.corookie.project.application.repository.ProjectRepositoryImpl;
import com.fourttttty.corookie.project.infrastructure.ProjectJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private IssueJpaRepository issueJpaRepository;
    @Autowired
    private ProjectJpaRepository projectJpaRepository;
    @Autowired
    private MemberJpaRepository memberJpaRepository;
    @Autowired
    private PlanJpaRepository planJpaRepository;

    @Autowired
    private PlanCategoryJpaRepository planCategoryJpaRepository;

    @Bean
    public IssueRepository issueRepository() {
        return new IssueRepositoryImpl(issueJpaRepository);
    }

    @Bean
    public ProjectRepository projectRepository() {
        return new ProjectRepositoryImpl(projectJpaRepository);
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemberRepositoryImpl(memberJpaRepository);
    }

    @Bean
    public PlanRepository planRepository(){
        return new PlanRepositoryImpl(planJpaRepository);
    }

    @Bean
    public PlanCategoryRepository planCategoryRepository(){
        return new PlanCategoryRepositoryImpl(planCategoryJpaRepository);
    }

}
