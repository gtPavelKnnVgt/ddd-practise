package com.study.medic.healersproject.app.impl;

import com.study.medic.healersproject.app.api.*;
import com.study.medic.healersproject.domain.Department;
import com.study.medic.healersproject.domain.Doctor;
import com.study.medic.healersproject.domain.TimeSlot;
import com.study.medic.healersproject.domain.WorkingSlot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateDepartmentUseCase implements UpdateDepartmentInbound {
    private final DepartmentRepository departmentRepository;

    @Override
    @Transactional
    public Department update(String dayOfWeek, LocalDateTime startTime, LocalDateTime endTime, Long doctorId) {
        log.info("Updating department by doctor id: {}", doctorId);
        Department department = departmentRepository.getDepartmentByDoctorId(doctorId);
        Doctor doctor = department.getDoctors().stream().filter(doc -> doctorId.equals(doc.getId()))
                .findFirst().orElseThrow(() -> new DoctorNotFoundException(doctorId));
        TimeSlot timeSlot = TimeSlot.create(startTime, endTime);
        WorkingSlot workingSlot = WorkingSlot.create(dayOfWeek, timeSlot);
        doctor.updateAvailability(workingSlot);
        return departmentRepository.saveAndFlush(department);
    }
}
