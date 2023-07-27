package com.fourttttty.corookie.plan.dto.request;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public record PlanUpdateRequest(@NotNull String planName,
        @NotNull String description,
        @NotNull LocalDateTime planStart,
        @NotNull LocalDateTime planEnd,
        @NotNull List<PlanCategoryUpdateRequest> categories) {

}
