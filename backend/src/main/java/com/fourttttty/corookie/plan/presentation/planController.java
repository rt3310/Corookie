package com.fourttttty.corookie.plan.presentation;

import com.fourttttty.corookie.plan.application.service.PlanService;
import com.fourttttty.corookie.plan.dto.request.PlanRequest;

import com.fourttttty.corookie.plan.dto.response.PlanResponse;
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
    public ResponseEntity<PlanResponse> planDetail(@PathVariable(name = "planId") Long id) {
        return ResponseEntity.ok(planService.findById(id));
    }

    @PostMapping("/")
    public ResponseEntity<PlanResponse> planCreate(@RequestBody PlanRequest planRequest) {
        return ResponseEntity.ok(planService.createPlan(planRequest));
    }

    @PutMapping("/{planId}")
    public ResponseEntity<Object> planModify(@PathVariable Long planId, @RequestBody PlanRequest planRequest) {
        planService.modifyPlan(planId, planRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<Object> planDelete(@PathVariable Long planId) {
        planService.deletePlan(planId);
        return ResponseEntity.ok().build();
    }
}
