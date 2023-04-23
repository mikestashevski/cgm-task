package com.cgm.task.controllers.dtos.enums;

public enum VisitReason {
    FIRST_VISIT, RECURRING_VISIT, URGENT;

    public static VisitReason from(String reason) {
        for (VisitReason visitReason : VisitReason.values()) {
            if (visitReason.name().equalsIgnoreCase(reason)) {
                return visitReason;
            }
        }
        return null;
    }
}
