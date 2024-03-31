package com.study.medic.healersproject.adapter.rest.v1.dto;

import lombok.Data;

@Data
public class CreateAppointmentDto {
    private Long patientId;
    private Long doctorId;
    private TimeSlotDto timeSlotToReserve;
    private Boolean isAccepted;
    private String dayOfWeek;
}