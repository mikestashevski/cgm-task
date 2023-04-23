package com.cgm.task.model;

import com.cgm.task.controllers.dtos.PatientDTO;
import com.nosaiii.sjorm.Model;
import com.nosaiii.sjorm.annotations.SJORMTable;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.sql.ResultSet;

@SJORMTable(tableName = "patients")
@NoArgsConstructor
public class Patient extends Model {

    public Patient(ResultSet rs) {
        super(rs);
    }

    public static Patient of(PatientDTO patientDTO, long doctorId) {
        Patient patient = new Patient();
        patient.setProperty("doctor_id", BigInteger.valueOf(doctorId));
        patient.setProperty("name", patientDTO.getName());
        patient.setProperty("surname", patientDTO.getSurname());
        patient.setProperty("date_of_birth", patientDTO.getDateOfBirth());
        patient.setProperty("social_security_number", patientDTO.getSocialSecurityNumber());
        return patient;
    }
}
