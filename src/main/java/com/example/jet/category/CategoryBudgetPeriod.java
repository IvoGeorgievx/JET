package com.example.jet.category;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CategoryBudgetPeriod {
    DAILY("Daily"),
    WEEKLY("Weekly"),
    MONTHLY("Monthly");

    private final String displayName;

    CategoryBudgetPeriod(String displayName) {
        this.displayName = displayName;
    }

    @JsonCreator
    public static CategoryBudgetPeriod fromDisplayName(String value) {
        for (CategoryBudgetPeriod period : CategoryBudgetPeriod.values()) {
            if (period.displayName.equalsIgnoreCase(value)) {
                return period;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }
}