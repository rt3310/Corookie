package com.fourttttty.corookie.plan.application.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.fourttttty.corookie.config.audit.JpaAuditingConfig;
import com.fourttttty.corookie.member.application.repository.MemberRepository;
import com.fourttttty.corookie.member.domain.AuthProvider;
import com.fourttttty.corookie.member.domain.Member;
import com.fourttttty.corookie.member.domain.Oauth2;
import com.fourttttty.corookie.plan.domain.CategoryInPlan;
import com.fourttttty.corookie.plan.domain.Plan;
import com.fourttttty.corookie.plan.domain.PlanMember;
import com.fourttttty.corookie.project.application.repository.ProjectRepository;
import com.fourttttty.corookie.project.domain.Project;
import com.fourttttty.corookie.support.TestConfig;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import({JpaAuditingConfig.class, TestConfig.class})
public class PlanMemberRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private PlanMemberRepository planMemberRepository;

    Member member = Member.of("name", "email", Oauth2.of(AuthProvider.KAKAO, "account"));
    List<Member> members = new ArrayList<>() {
        {
            Member.of("testMember1", "email", Oauth2.of(AuthProvider.KAKAO, "account"));
            Member.of("testMember2", "email", Oauth2.of(AuthProvider.KAKAO, "account"));
            Member.of("testMember3", "email", Oauth2.of(AuthProvider.KAKAO, "account"));
        }
    };
    Project project = Project.of("name",
        "description",
        true,
        "http://test.com",
        false,
        member);
    Plan plan = Plan.of("testPlan",
        "planDescription",
        LocalDateTime.now().minusDays(2),
        LocalDateTime.now(),
        true,
        project
    );

    @BeforeEach
    void setUp() {
        memberRepository.save(member);
        projectRepository.save(project);
        members.stream()
            .forEach(memberRepository::save);
        planRepository.save(plan);
    }

    @Test
    @DisplayName("멤버_일정을 저장한다.")
    void save() {
        // given
        PlanMember planMember = PlanMember.of(members.get(0), plan);

        // when
        PlanMember foundPlanMember = planMemberRepository.save(planMember);

        // then
        assertThat(foundPlanMember).isNotNull();
        assertThat(foundPlanMember.getId().getPlan()).isEqualTo(plan);
        assertThat(foundPlanMember.getId().getMember()).isEqualTo(members.get(0));
    }

    @Test
    @DisplayName("일정id에 따른 멤버_일정 조회")
    void findByPlanId() {
        // given
        members.stream()
            .map(member->PlanMember.of(member,plan))
            .forEach(planMemberRepository::save);

        // when
        List<PlanMember> foundPlanMembers = planMemberRepository.findByPlanId(1L);


        // then
        assertThat(foundPlanMembers).isNotNull();
        foundPlanMembers.stream()
            .map(planMember -> planMember.getId().getPlan())
            .forEach(planMemberPlan -> assertThat(planMemberPlan).isEqualTo(plan));
        assertThat(foundPlanMembers.stream()
            .map(planMember -> planMember.getId().getMember())
            .toList()).isEqualTo(members);
    }

    @Test
    @DisplayName("멤버_일정 Id에 따른 존재 여부 조회")
    void exists() {
        // given
        planMemberRepository.save(PlanMember.of(member,plan));

        // when
        Boolean exist = planMemberRepository.exists(PlanMember.of(member,plan).getId());

        // then
        assertThat(exist).isTrue();
    }

    @Test
    @DisplayName("멤버_일정 삭제")
    void delete() {
        // given
        PlanMember planMember = PlanMember.of(member, plan);
        planMemberRepository.save(planMember);

        // when
        planMemberRepository.delete(planMember);

        // then
        Boolean exist = planMemberRepository.exists(planMember.getId());
        assertThat(exist).isFalse();
    }
}
