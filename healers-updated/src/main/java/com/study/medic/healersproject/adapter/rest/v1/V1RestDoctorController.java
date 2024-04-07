package com.study.medic.healersproject.adapter.rest.v1;

import com.study.medic.healersproject.adapter.rest.v1.dto.CreateDoctorDto;
import com.study.medic.healersproject.adapter.rest.v1.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/doctors")
public class V1RestDoctorController {
    private final CreateDoctorInbound createDoctorInbound;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto createDoctor(@RequestBody CreateDoctorDto dto) {
        LocalDateTime startTime = dto.getWorkingSlot().getTimeSlot().getStartTime();
        LocalDateTime endTime = dto.getWorkingSlot().getTimeSlot().getEndTime();
        var doctor = createDoctorInbound.create(dto.getWorkingSlot().getDayOfWeek(), startTime, endTime,
                dto.getFirstName(), dto.getLastName(), dto.getSpecialization(), dto.getLicenseNumber());
        return new ResponseDto(doctor.getId());
    }
}
