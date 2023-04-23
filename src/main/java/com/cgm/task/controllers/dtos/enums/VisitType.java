package com.cgm.task.controllers.dtos.enums;

public enum VisitType {
    HOME, DOCTOR_OFFICE;

    public static VisitType from(String visitType) {
        for (VisitType visitTypeEnum : VisitType.values()) {
            if (visitTypeEnum.name().equalsIgnoreCase(visitType)) {
                return visitTypeEnum;
            }
        }
        return null;
    }
}
