package com.cgm.task.controllers.dtos;

import lombok.Data;

@Data
public class DoctorDTO {
    private long id;
    private String name;
    private String surname;
    private String username;
    private String passwordHash;

}
