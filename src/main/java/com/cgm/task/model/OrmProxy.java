package com.cgm.task.model;

import com.nosaiii.sjorm.Query;
import com.nosaiii.sjorm.SJORM;
import com.nosaiii.sjorm.metadata.ModelMetadata;
import lombok.Getter;
import org.springframework.stereotype.Repository;

@Repository
public class OrmProxy {

    private final SJORM sjorm = SJORM.getInstance();

    @Getter
    private Query<Doctor> doctors;
    @Getter
    private Query<Patient> patients;
    @Getter
    private Query<Visit> visits;

    private OrmProxy() {
        sjorm.registerModel(new ModelMetadata(Doctor.class));
        sjorm.registerModel(new ModelMetadata(Patient.class));
        sjorm.registerModel(new ModelMetadata(Visit.class));
        doctors = sjorm.getAll(Doctor.class);
        patients = sjorm.getAll(Patient.class);
        visits = sjorm.getAll(Visit.class);
    }
}