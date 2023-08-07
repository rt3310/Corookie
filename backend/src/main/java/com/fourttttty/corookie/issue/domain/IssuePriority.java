package com.fourttttty.corookie.issue.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fourttttty.corookie.global.exception.InvalidIssuePriorityException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IssuePriority {
    HIGHEST(1, "Highest"),
    HIGH(2, "High"),
    NORMAL(3, "Normal"),
    LOW(4, "Low"),
    LOWEST(5, "Lowest");

    private final int value;
    private final String name;

    @JsonCreator
    public static IssuePriority from(String name) {
        for (IssuePriority issuePriority : IssuePriority.values()) {
            if (issuePriority.getName().equals(name)) {
                return issuePriority;
            }
        }
        throw new InvalidIssuePriorityException();
    }

    public static IssuePriority from(int value) {
        for (IssuePriority issuePriority : IssuePriority.values()) {
            if (issuePriority.getValue() == value) {
                return issuePriority;
            }
        }
        throw new InvalidIssuePriorityException();
    }

    @JsonValue
    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }
}
