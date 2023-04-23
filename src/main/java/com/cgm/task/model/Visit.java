package com.cgm.task.model;

import com.cgm.task.controllers.dtos.VisitDTO;
import com.cgm.task.controllers.dtos.enums.VisitReason;
import com.cgm.task.controllers.dtos.enums.VisitType;
import com.nosaiii.sjorm.Model;
import com.nosaiii.sjorm.annotations.SJORMTable;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.sql.ResultSet;

@SJORMTable(tableName = "visits")
@NoArgsConstructor
public class Visit extends Model {

    public Visit(ResultSet rs) {
        super(rs);
    }

    public static Visit of(VisitDTO visitDTO) {
        Visit visit = new Visit();
        visit.setProperty("doctor_id", BigInteger.valueOf(visitDTO.getDoctorId()));
        visit.setProperty("patient_id", BigInteger.valueOf(visitDTO.getPatientId()));
        visit.setProperty("start_time", visitDTO.getStartTime());
        visit.setProperty("end_time", visitDTO.getEndTime());
        visit.setProperty("reason",
                visitDTO.getReason() == null ? VisitReason.RECURRING_VISIT.name() : visitDTO.getReason().name());
        visit.setProperty("type",
                visitDTO.getType() == null ? VisitType.DOCTOR_OFFICE.name() : visitDTO.getType().name());
        visit.setProperty("family_history", visitDTO.getFamilyHistory());
        return visit;
    }
}
