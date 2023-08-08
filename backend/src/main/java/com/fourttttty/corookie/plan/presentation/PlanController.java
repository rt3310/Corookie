package com.fourttttty.corookie.plan.presentation;

import com.fourttttty.corookie.plan.application.service.PlanService;
import com.fourttttty.corookie.plan.dto.request.PlanCategoryCreateRequest;
import com.fourttttty.corookie.plan.dto.request.PlanCreateRequest;

import com.fourttttty.corookie.plan.dto.request.PlanMemberCreateRequest;
import com.fourttttty.corookie.plan.dto.request.PlanMemberDeleteRequest;
import com.fourttttty.corookie.plan.dto.request.PlanUpdateRequest;
import com.fourttttty.corookie.plan.dto.response.CalendarPlanResponse;
import com.fourttttty.corookie.plan.dto.response.PlanCategoryResponse;
import com.fourttttty.corookie.plan.dto.response.PlanMemberResponse;
import com.fourttttty.corookie.plan.dto.response.PlanResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/projects/{projectId}/plans")
public class PlanController {
    private final PlanService planService;

    @GetMapping
    public ResponseEntity<List<CalendarPlanResponse>> planCalendarList(@PathVariable Long projectId, @RequestParam LocalDate date) {
        return ResponseEntity.ok(planService.findByDate(date));
    }

    @GetMapping("/{planId}")
    public ResponseEntity<PlanResponse> planDetail(@PathVariable Long planId) {
        return ResponseEntity.ok(planService.findById(planId));
    }

    @PostMapping
    public ResponseEntity<PlanResponse> planCreate(@PathVariable Long projectId,
                                                   @RequestBody @Validated PlanCreateRequest request) {
        return ResponseEntity.ok(planService.createPlan(request, projectId));
    }

    @PutMapping("/{planId}")
    public ResponseEntity<PlanResponse> planModify(@PathVariable Long projectId,
                                                   @PathVariable Long planId,
                                                   @RequestBody @Validated PlanUpdateRequest request) {
        return ResponseEntity.ok(planService.modifyPlan(request, planId, projectId));
    }

    @DeleteMapping("/{planId}")
    public ResponseEntity<Object> planDelee(@PathVariable Long planId) {
        planService.deletePlan(planId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{planId}/categories")
    public ResponseEntity<PlanCategoryResponse> categoryCreate(@PathVariable Long planId,
                                                               @RequestBody @Validated PlanCategoryCreateRequest request) {
        return ResponseEntity.ok(planService.createPlanCategory(planId, request));
    }

    @DeleteMapping("/{planId}/categories")
    public ResponseEntity<Object> categoryDelete(@PathVariable Long planId,
                                                 @RequestBody @Validated PlanCategoryCreateRequest request) {
        planService.deletePlanCategory(planId, request.content());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{planId}/members")
    public ResponseEntity<PlanMemberResponse> memberCreate(@PathVariable Long planId,
                                                           @RequestBody @Validated PlanMemberCreateRequest request) {
        return ResponseEntity.ok(planService.createPlanMember(planId, request));
    }

    @DeleteMapping("/{planId}/members")
    public ResponseEntity<Object> memberDelete(@PathVariable Long planId,
                                               @RequestBody @Validated PlanMemberDeleteRequest request) {
        planService.deletePlanMember(planId, request);
        return ResponseEntity.noContent().build();
    }
}
