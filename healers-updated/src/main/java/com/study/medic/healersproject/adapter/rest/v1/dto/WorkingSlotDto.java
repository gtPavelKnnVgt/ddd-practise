package com.study.medic.healersproject.adapter.rest.v1.dto;

import lombok.Data;

@Data
public class WorkingSlotDto {
    private String dayOfWeek;
    private TimeSlotDto timeSlot;
}
