package com.cgm.task.controllers;

import com.cgm.task.controllers.dtos.PatientDTO;
import com.cgm.task.services.AuthorizationService;
import com.cgm.task.services.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;
    private final AuthorizationService authorizationService;

    @GetMapping
    public List<PatientDTO> getAllPatients(@RequestHeader("Authorization") String authorizationHeader) {
        long doctorId = authorizationService.authorizeDoctor(authorizationHeader);
        return patientService.getPatients(doctorId);
    }

    @PostMapping
    public void createPatient(@RequestBody PatientDTO patient, @RequestHeader("Authorization") String authorizationHeader) {
        long doctorId = authorizationService.authorizeDoctor(authorizationHeader);
        patientService.createPatient(patient, doctorId);
    }
}
