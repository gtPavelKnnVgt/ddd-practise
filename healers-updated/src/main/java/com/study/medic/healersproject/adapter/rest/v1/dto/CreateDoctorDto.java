package com.study.medic.healersproject.adapter.rest.v1.dto;

import com.study.medic.healersproject.domain.DoctorSpecialization;
import lombok.Data;

@Data
public class CreateDoctorDto {
    private String firstName;
    private String lastName;
    private DoctorSpecialization specialization;
    private String licenseNumber;
    private WorkingSlotDto workingSlot;
}
