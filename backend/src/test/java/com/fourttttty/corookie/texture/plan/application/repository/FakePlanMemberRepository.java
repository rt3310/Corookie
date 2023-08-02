package com.fourttttty.corookie.texture.plan.application.repository;

import com.fourttttty.corookie.plan.application.repository.PlanMemberRepository;
import com.fourttttty.corookie.plan.domain.Plan;
import com.fourttttty.corookie.plan.domain.PlanMember;
import com.fourttttty.corookie.plan.domain.PlanMemberId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;

public class FakePlanMemberRepository implements PlanMemberRepository {

    class Id{
        public Long planId;
        public Long memberId;

        @Override
        public boolean equals(Object o) {
            PlanMember planMember = (PlanMember) o;
            return planId.equals(planMember.getId().getPlan().getId()) &&
                memberId.equals(planMember.getId().getMember().getId());
        }
        @Override
        public int hashCode() {
            return Objects.hash(planId,memberId);
        }

        private Id(PlanMemberId planMemberId){
            this.planId = planMemberId.getPlan().getId();
            this.memberId = planMemberId.getMember().getId();
        }
    }
    private final Map<Id, PlanMember> store = new HashMap<>();
    @Override
    public List<PlanMember> findAllbyPlan(Plan plan) {
        return store.entrySet().stream()
            .filter(entry-> entry.getKey().planId.equals(plan.getId()))
            .map(entry->store.get(entry.getKey()))
            .toList();
    }

    @Override
    public PlanMember save(PlanMember planMember) {
        Optional<Entry<Id, PlanMember>> first = store.entrySet().stream()
            .filter(entry->entry.equals(planMember))
            .findFirst();
        if(first.isEmpty()){
            System.out.println(new Id(planMember.getId()));
            return store.put(new Id(planMember.getId()),planMember);
        }
        return first.get().getValue();
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
