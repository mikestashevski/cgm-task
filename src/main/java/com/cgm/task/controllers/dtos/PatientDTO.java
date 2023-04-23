package com.cgm.task.controllers.dtos;

import com.cgm.task.model.Patient;
import lombok.Data;

import java.math.BigInteger;

@Data
public class PatientDTO {
    private long id;
    private long doctorId;
    private String name;
    private String surname;
    private long dateOfBirth;
    private String socialSecurityNumber;

    public static PatientDTO of(Patient patient) {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(patient.getProperty("id", BigInteger.class).longValue());
        patientDTO.setDoctorId(patient.getProperty("doctor_id", BigInteger.class).longValue());
        patientDTO.setName(patient.getProperty("name", String.class));
        patientDTO.setSurname(patient.getProperty("surname", String.class));
        patientDTO.setDateOfBirth(patient.getProperty("date_of_birth", Long.class));
        patientDTO.setSocialSecurityNumber(patient.getProperty("social_security_number", String.class));
        return patientDTO;
    }

}
