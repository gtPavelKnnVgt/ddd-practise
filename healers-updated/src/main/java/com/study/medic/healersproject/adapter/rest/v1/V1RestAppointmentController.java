package com.study.medic.healersproject.adapter.rest.v1;

import com.study.medic.healersproject.adapter.rest.v1.dto.CreateAppointmentDto;
import com.study.medic.healersproject.adapter.rest.v1.dto.ResponseDto;
import com.study.medic.healersproject.app.api.CreateAppointmentInbound;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/appointments")
public class V1RestAppointmentController {
    private final CreateAppointmentInbound createAppointmentInbound;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDto createAppointment(@RequestBody CreateAppointmentDto dto) {
        LocalDateTime startTime = dto.getTimeSlotToReserve().getStartTime();
        LocalDateTime endTime = dto.getTimeSlotToReserve().getEndTime();
        var appointment = createAppointmentInbound.create(dto.getPatientId(),
                dto.getDoctorId(), startTime, endTime, dto.getDayOfWeek(), dto.getIsAccepted());
        return new ResponseDto(appointment.getId());
    }
}
