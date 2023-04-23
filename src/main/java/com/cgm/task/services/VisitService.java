package com.cgm.task.services;

import com.cgm.task.controllers.dtos.VisitDTO;
import com.cgm.task.model.OrmProxy;
import com.cgm.task.model.Visit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitService {

    private final OrmProxy ormProxy;

    public List<VisitDTO> getVisitsByPatientId(long patientId) {
        List<Visit> visitList = ormProxy.getVisits().where(
                        visit -> visit.getProperty("patient_id", BigInteger.class).longValue() == patientId)
                .toList();
        List<VisitDTO> visitDTOS = new ArrayList<>();
        for (Visit visit : visitList) {
            visitDTOS.add(VisitDTO.of(visit));
        }
        return visitDTOS;
    }

    public VisitDTO createVisit(VisitDTO visitDTO) {
        checkIfAnotherVisitOverlaps(visitDTO, getAllVisitsForDoctor(visitDTO.getDoctorId()));
        checkAndSetVisitDefaults(visitDTO);
        Visit visit = Visit.of(visitDTO);
        visit.save();
        visit = ormProxy.getVisits().last();
        return VisitDTO.of(visit);
    }

    private void checkAndSetVisitDefaults(VisitDTO visitDTO) {
        if (visitDTO.getPatientId() == 0) {
            visitDTO.setPatientId(1);
        }
        if (visitDTO.getDoctorId() == 0) {
            visitDTO.setDoctorId(1);
        }
        if (visitDTO.getStartTime() == 0 && visitDTO.getEndTime() == 0) {
            visitDTO.setEndTime(3600);
        }
    }

    private void checkIfAnotherVisitOverlaps(VisitDTO newVisitDTO, List<Visit> visitList) {
        for (Visit visit : visitList) {
            boolean startTimeOverlap = newVisitDTO.getStartTime() >= visit.getProperty("start_time", Long.class) && newVisitDTO.getStartTime() < visit.getProperty("end_time", Long.class);
            boolean endTimeOverlap = newVisitDTO.getEndTime() > visit.getProperty("start_time", Long.class) && newVisitDTO.getEndTime() <= visit.getProperty("end_time", Long.class);
            boolean encompassingVisit = newVisitDTO.getStartTime() <= visit.getProperty("start_time", Long.class) && newVisitDTO.getEndTime() >= visit.getProperty("end_time", Long.class);

            if (startTimeOverlap || endTimeOverlap || encompassingVisit) {
                throw new IllegalArgumentException("This timeframe is already taken");
            }
        }
    }

    List<Visit> getAllVisitsForDoctor(long doctorId) {
        return ormProxy.getVisits().where(visitPred -> visitPred.getProperty("doctor_id", BigInteger.class).longValue() == doctorId).toList();
    }

    public VisitDTO updateVisit(VisitDTO visitDTO) {
        checkIfAnotherVisitOverlaps(visitDTO, getAllVisitsForDoctor(visitDTO.getDoctorId()));

        Visit visit = ormProxy.getVisits().where(
                visitPred -> visitPred.getProperty("id", BigInteger.class)
                        .longValue() == visitDTO.getId()).first();

        visit.setProperty("start_time", visitDTO.getStartTime());
        visit.setProperty("end_time", visitDTO.getEndTime());
        visit.setProperty("reason", visitDTO.getReason().name());
        visit.setProperty("type", visitDTO.getType().name());
        visit.setProperty("family_history", visitDTO.getFamilyHistory());
        visit.save();
        return VisitDTO.of(visit);
    }

}
