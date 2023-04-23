package com.cgm.task.services;

import com.cgm.task.controllers.dtos.VisitDTO;
import com.cgm.task.model.Visit;
import com.nosaiii.sjorm.SJORM;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class VisitServiceTest {

    @Mock
    private VisitService visitService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCheckIfAnotherVisitOverlaps() {
        // Arrange
        VisitDTO visitDTO1 = new VisitDTO();
        visitDTO1.setDoctorId(1);
        visitDTO1.setPatientId(1);
        visitDTO1.setStartTime(1000);
        visitDTO1.setEndTime(2000);

        VisitDTO visitDTO2 = new VisitDTO();
        visitDTO2.setDoctorId(1);
        visitDTO2.setPatientId(2);
        visitDTO2.setStartTime(1500);
        visitDTO2.setEndTime(2500);

        MockedStatic<SJORM> sjormMockedStatic = Mockito.mockStatic(SJORM.class);
        sjormMockedStatic.when(SJORM::getInstance).thenReturn(Mockito.mock(SJORM.class));
        try (sjormMockedStatic) {
            List<Visit> visits = new ArrayList<>();
            Visit visit = new Visit();
            visit.setProperty("id", BigInteger.valueOf(1));
            visit.setProperty("doctor_id", BigInteger.valueOf(visitDTO1.getDoctorId()));
            visit.setProperty("patient_id", BigInteger.valueOf(visitDTO1.getPatientId()));
            visit.setProperty("start_time", visitDTO1.getStartTime());
            visit.setProperty("end_time", visitDTO1.getEndTime());
            visits.add(visit);

            when(visitService.getAllVisitsForDoctor(anyLong())).thenReturn(visits);
            when(visitService.createVisit(any())).thenCallRealMethod();

            // Act & Assert
            assertThrows(IllegalArgumentException.class, () -> {
                visitService.createVisit(visitDTO2);
            });
        }
    }
}
