package com.study.medic.healersproject.adapter.rest.v1.dto;

import lombok.Data;

@Data
public class UpdateDepartmentDto {
    private WorkingSlotDto workingSlotDto;
    private Long doctorId;
}
