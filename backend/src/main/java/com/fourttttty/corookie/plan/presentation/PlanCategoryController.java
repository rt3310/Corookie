package com.fourttttty.corookie.plan.presentation;

import com.fourttttty.corookie.plan.application.service.PlanCategoryService;
import com.fourttttty.corookie.plan.application.service.PlanService;
import com.fourttttty.corookie.plan.dto.request.PlanCategoryCreateRequest;
import com.fourttttty.corookie.plan.dto.response.PlanCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects/{projectId}/plans/{planId}/categories")
@RequiredArgsConstructor
public class PlanCategoryController {

    private final PlanService planService;
    private final PlanCategoryService planCategoryService;

    @GetMapping
    public ResponseEntity<List<PlanCategoryResponse>> categoryList(@PathVariable Long projectId) {
        return ResponseEntity.ok(planCategoryService.findByProjectId(projectId));
    }

    @PostMapping
    public ResponseEntity<PlanCategoryResponse> categoryCreate(@PathVariable Long projectId,
                                                               @PathVariable Long planId,
                                                               @RequestBody @Validated PlanCategoryCreateRequest request) {
        return ResponseEntity.ok(planService.createPlanCategory(projectId, planId, request));
    }

    @DeleteMapping
    public ResponseEntity<Object> categoryDelete(@PathVariable Long planId,
                                                 @RequestBody @Validated PlanCategoryCreateRequest request) {
        planService.deletePlanCategory(planId, request.content());
        return ResponseEntity.noContent().build();
    }

}
