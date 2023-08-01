package com.fourttttty.corookie.plan.presentation;

import com.fourttttty.corookie.plan.application.service.PlanService;
import com.fourttttty.corookie.plan.dto.request.PlanCreateRequest;

import com.fourttttty.corookie.plan.dto.request.PlanUpdateRequest;
import com.fourttttty.corookie.plan.dto.response.PlanCategoryResponse;
import com.fourttttty.corookie.plan.dto.response.PlanResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/projects/{projectId}/plans")
public class PlanController {
    private final PlanService planService;

    @GetMapping("/{planId}")
    public ResponseEntity<PlanResponse> planDetail(@PathVariable Long planId) {
        return ResponseEntity.ok(planService.findById(planId));
    }

    @PostMapping
    public ResponseEntity<PlanResponse> planCreate(@PathVariable Long projectId,
                                                   @RequestBody @Validated PlanCreateRequest planCreateRequest) {
        return ResponseEntity.ok(planService.createPlan(planCreateRequest, projectId));
    }

    @PutMapping("/{planId}")
    public ResponseEntity<PlanResponse> planModify(@PathVariable Long projectId,
                                             @PathVariable Long planId,
                                             @RequestBody @Validated PlanUpdateRequest planUpdateRequest) {
        return ResponseEntity.ok(planService.modifyPlan(planUpdateRequest, planId, projectId));
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<Object> planDelete(@PathVariable Long planId) {
        planService.deletePlan(planId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{planId}/categories")
    public ResponseEntity<PlanCategoryResponse> categoryCreate(@PathVariable Long planId,
        @RequestParam("categoryContent") String content){
        return ResponseEntity.ok(planService.createPlanCategory(planId,content));
    }

    @DeleteMapping("/{planId}/categories")
    public ResponseEntity<Object> categoryDelete(@PathVariable Long planId,
        @RequestParam("categoryContent") String content){
        planService.deleteCategory(planId,content);
        return ResponseEntity.noContent().build();
    }
}
