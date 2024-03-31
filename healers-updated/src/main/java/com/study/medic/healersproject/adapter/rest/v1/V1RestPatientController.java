package com.study.medic.healersproject.adapter.rest.v1;

import com.study.medic.healersproject.adapter.rest.v1.dto.CreatePatientDto;
import com.study.medic.healersproject.adapter.rest.v1.dto.ResponseDto;
import com.study.medic.healersproject.app.api.CreatePatientInbound;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/patients")
public class V1RestPatientController {
    private final CreatePatientInbound createPatientInbound;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto createPatient(@RequestBody CreatePatientDto dto) {
        var patient = createPatientInbound.create(dto.getFirstName(), dto.getLastName(), dto.getBirthDate(),
                dto.getMedicalRecordNumber());
        return new ResponseDto(patient.getId());
    }
}
