package com.cgm.task.controllers;

import com.cgm.task.controllers.dtos.VisitDTO;
import com.cgm.task.services.AuthorizationService;
import com.cgm.task.services.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patients/{patientId}/visits")
public class VisitController {

    private final VisitService visitService;
    private final AuthorizationService authorizationService;

    @GetMapping
    public List<VisitDTO> getVisitsByPatientId(@PathVariable long patientId, @RequestHeader("Authorization") String authorizationHeader) {
        authorizationService.authorizeDoctor(authorizationHeader);
        return visitService.getVisitsByPatientId(patientId);
    }

    @PostMapping
    public VisitDTO createVisit(@PathVariable(name = "", value = "patientId") long ignoredPatientId, @RequestBody VisitDTO visit, @RequestHeader("Authorization") String authorizationHeader) {
        authorizationService.authorizeDoctor(authorizationHeader);
        return visitService.createVisit(visit);
    }

    @PutMapping("/{ignoredVisitId}")
    public VisitDTO updateVisit(@PathVariable(name = "", value = "patientId") long ignoredPatientId, @PathVariable Long ignoredVisitId, @RequestBody VisitDTO visit, @RequestHeader("Authorization") String authorizationHeader) {
        authorizationService.authorizeDoctor(authorizationHeader);
        return visitService.updateVisit(visit);
    }
}