package com.study.medic.healersproject.adapter.rest.v1;

import com.study.medic.healersproject.adapter.rest.v1.dto.ResponseDto;
import com.study.medic.healersproject.adapter.rest.v1.dto.UpdateDepartmentDto;
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

        return new ResponseDto(appointment.getId());
    }
}
