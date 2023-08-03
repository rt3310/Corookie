package com.fourttttty.corookie.texture.plan.application.repository;

import com.fourttttty.corookie.plan.application.repository.PlanMemberRepository;
import com.fourttttty.corookie.plan.domain.Plan;
import com.fourttttty.corookie.plan.domain.PlanMember;
import com.fourttttty.corookie.plan.domain.PlanMemberId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FakePlanMemberRepository implements PlanMemberRepository {
    private final Map<Id, PlanMember> store = new HashMap<>();
    class Id{
        public Long planId;
        public Long memberId;

        @Override
        public int hashCode() {
            return Objects.hash(this.planId,this.memberId);
        }

        private Id(PlanMemberId planMemberId){
            this.planId = planMemberId.getPlan().getId();
            this.memberId = planMemberId.getMember().getId();
        }
    }
    @Override
    public List<PlanMember> findAllbyPlan(Plan plan) {
        return store.entrySet().stream()
            .filter(entry-> entry.getKey().planId.equals(plan.getId()))
            .map(entry->store.get(entry.getKey()))
            .toList();
    }
    @Override
    public PlanMember save(PlanMember planMember) {
        return store.put(new Id(planMember.getId()),planMember);
    }

    @Override
    public Boolean exists(PlanMemberId planMemberId) {
        return store.containsKey(new Id(planMemberId));
    }

    @Override
    public void delete(PlanMember planMember) {
        store.remove(planMember);
    }
}
