package com.study.medic.healersproject.adapter.rest.v1;

import com.study.medic.healersproject.adapter.rest.v1.dto.ResponseDto;
import com.study.medic.healersproject.adapter.rest.v1.dto.UpdateDepartmentDto;
import com.study.medic.healersproject.app.api.UpdateDepartmentInbound;
import com.study.medic.healersproject.domain.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/departments")
public class V1RestDepartmentController {
    private final UpdateDepartmentInbound updateDepartmentInbound;

    @PostMapping
    public ResponseDto updateDepartment(@RequestBody UpdateDepartmentDto dto) {
        Department department = updateDepartmentInbound.update(dto.getWorkingSlotDto().getDayOfWeek(), dto.getWorkingSlotDto().getTimeSlot().getStartTime(),
                dto.getWorkingSlotDto().getTimeSlot().getEndTime(), dto.getDoctorId());
        return new ResponseDto(department.getId());
    }
}
