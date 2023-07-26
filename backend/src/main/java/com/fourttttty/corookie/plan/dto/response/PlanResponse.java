package com.fourttttty.corookie.plan.dto.response;

import com.fourttttty.corookie.plan.domain.Plan;

import jakarta.validation.constraints.NotNull;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PlanResponse(@NotNull  Long planId,
                           @NotNull String planName,
                           @NotNull String description,
                           @NotNull LocalDateTime planStart,
                           @NotNull LocalDateTime planEnd) {

    public static PlanResponse of(Plan plan) {
        return PlanResponse.builder()
                .planId(plan.getId())
                .planName(plan.getPlanName())
                .planStart(plan.getPlanStart())
                .planEnd(plan.getPlanEnd())
                .build();
    }
}

