package com.cgm.task.services;

import com.cgm.task.controllers.dtos.PatientDTO;
import com.cgm.task.model.OrmProxy;
import com.cgm.task.model.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final OrmProxy ormProxy;

    public List<PatientDTO> getPatients(long doctorId) {
        List<Patient> patients = ormProxy.getPatients()
                .where(patient -> patient.getProperty("doctor_id").
                        toString().equals(Long.toString(doctorId))).toList();
        List<PatientDTO> patientDTOs = new ArrayList<>();
        for (Patient patient : patients) {
            patientDTOs.add(PatientDTO.of(patient));
        }
        return patientDTOs;
    }

    public void createPatient(PatientDTO patientDTO, long doctorId) {
        Patient patient = Patient.of(patientDTO, doctorId);
        patient.save();
    }

}
