package com.fourttttty.corookie.plan.presentation;


import com.fourttttty.corookie.plan.application.service.PlanService;
import com.fourttttty.corookie.plan.domain.Plan;
import com.fourttttty.corookie.plan.dto.request.PlanRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/projects/{projectId}/plans")
public class planController {
    private final PlanService planService;

    @GetMapping("/{planId}")
    public ResponseEntity<Plan> planDetail(@PathVariable(name = "planId") Long id){
        return ResponseEntity.ok(planService.findById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<Plan>> planListByProjectId(@PathVariable Long projectId){
        return ResponseEntity.ok(planService.findByProjcetId(projectId));
    }

    @PostMapping("/")
    public ResponseEntity<Plan> planCreate(@RequestBody PlanRequest planRequest){
        return ResponseEntity.ok(planService.createPlan(planRequest));
    }

    @PutMapping("/{planId}")
    public ResponseEntity<Object> planModify(@PathVariable Long planId, @RequestBody PlanRequest planRequest){
        planService.modifyPlan(planId, planRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<Object> planDelete(@PathVariable Long planId){
        planService.deletePlan(planId);
        return ResponseEntity.ok().build();
    }
}
