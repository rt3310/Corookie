package com.fourttttty.corookie.plan.dto.request;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;

public record PlanUpdateRequest(@NotNull String planName,
        @NotNull String description,
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        @NotNull LocalDateTime planStart,
        @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        @NotNull LocalDateTime planEnd,
        @NotNull List<PlanCategoryUpdateRequest> categories) {

}
