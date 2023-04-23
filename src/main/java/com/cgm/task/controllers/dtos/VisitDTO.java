package com.cgm.task.controllers.dtos;

import com.cgm.task.controllers.dtos.enums.VisitReason;
import com.cgm.task.controllers.dtos.enums.VisitType;
import com.cgm.task.model.Visit;
import lombok.Data;

import java.math.BigInteger;

@Data
public class VisitDTO {
    private long id;
    private long doctorId;
    private long patientId;
    private long startTime;
    private long endTime;
    private VisitReason reason;
    private VisitType type;
    private String familyHistory;

    public static VisitDTO of(Visit visit) {
        VisitDTO visitDTO = new VisitDTO();
        visitDTO.setId(visit.getProperty("id", BigInteger.class).longValue());
        visitDTO.setDoctorId(visit.getProperty("doctor_id", BigInteger.class).longValue());
        visitDTO.setStartTime(visit.getProperty("start_time", Long.class));
        visitDTO.setEndTime(visit.getProperty("end_time", Long.class));
        visitDTO.setReason(VisitReason.from(visit.getProperty("reason", String.class)));
        visitDTO.setType(VisitType.from(visit.getProperty("type", String.class)));
        visitDTO.setFamilyHistory(visit.getProperty("family_history", String.class));
        return visitDTO;
    }

}
