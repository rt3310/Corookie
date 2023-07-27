package com.fourttttty.corookie.issue.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fourttttty.corookie.global.exception.InvalidIssuePriorityException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IssuePriority {
    HIGHEST("Highest"),
    HIGH("High"),
    NORMAL("Normal"),
    LOW("Low"),
    LOWEST("Lowest");

    private final String value;

    @JsonCreator
    public static IssuePriority from(String value) {
        for (IssuePriority issuePriority : IssuePriority.values()) {
            if (issuePriority.getValue().equals(value)) {
                return issuePriority;
            }
        }
        throw new InvalidIssuePriorityException();
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }
}
