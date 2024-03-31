package com.study.medic.healersproject.adapter.rest.v1.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreatePatientDto {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String medicalRecordNumber;
}
