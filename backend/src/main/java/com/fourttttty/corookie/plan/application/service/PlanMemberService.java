package com.fourttttty.corookie.plan.application.service;

import com.fourttttty.corookie.member.application.service.MemberService;
import com.fourttttty.corookie.plan.application.repository.PlanMemberRepository;
import com.fourttttty.corookie.plan.domain.Plan;
import com.fourttttty.corookie.plan.domain.PlanMember;
import com.fourttttty.corookie.plan.dto.request.PlanMemberCreateRequest;
import com.fourttttty.corookie.plan.dto.response.PlanMemberResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlanMemberService {
    private final PlanMemberRepository planMemberRepository;
    private final MemberService memberService;

    @Transactional
    public PlanMemberResponse create(Plan plan, PlanMemberCreateRequest request){
        PlanMember planMember =planMemberRepository.save(PlanMember.of(memberService.findEntityById(request.id()),plan));
        return PlanMemberResponse.from(planMember);
    }

    public List<PlanMemberResponse> findAllByPlan(Plan plan){
        return planMemberRepository.findAllbyPlan(plan).stream()
            .map(planMember -> PlanMemberResponse.from(planMember))
            .toList();
    }

    @Transactional
    public void deletePlanMember(PlanMember planMember){
        planMemberRepository.delete(planMember);
    }
}
