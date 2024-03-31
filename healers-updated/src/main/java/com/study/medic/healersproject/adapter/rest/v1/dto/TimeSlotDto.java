package com.study.medic.healersproject.adapter.rest.v1.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TimeSlotDto {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
