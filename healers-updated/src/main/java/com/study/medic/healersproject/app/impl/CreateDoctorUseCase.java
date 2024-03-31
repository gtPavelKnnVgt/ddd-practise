package com.study.medic.healersproject.app.impl;

import com.study.medic.healersproject.app.api.CreateDoctorInbound;
import com.study.medic.healersproject.app.api.DoctorRepository;
import com.study.medic.healersproject.app.api.TimeSlotRepository;
import com.study.medic.healersproject.app.api.WorkingSlotRepository;
import com.study.medic.healersproject.domain.Doctor;
import com.study.medic.healersproject.domain.DoctorSpecialization;
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
public class CreateDoctorUseCase implements CreateDoctorInbound {
    private final DoctorRepository doctorRepository;
    private final WorkingSlotRepository workingSlotRepository;
    private final TimeSlotRepository timeSlotRepository;

    @Override
    @Transactional
    public Doctor create(String dayOfWeek, LocalDateTime startTime, LocalDateTime endTime, String firstName, String lastName,
                         DoctorSpecialization specialization, String licenseNumber) {
        log.info("Creating a doctor...");
        TimeSlot timeSlot = TimeSlot.create(startTime, endTime);
        timeSlotRepository.saveAndFlush(timeSlot);
        WorkingSlot workingSlot = WorkingSlot.create(dayOfWeek, timeSlot);
        workingSlotRepository.saveAndFlush(workingSlot);
        var doctor = Doctor.create(workingSlot, firstName, lastName, specialization, licenseNumber);
        return doctorRepository.saveAndFlush(doctor);
    }
}
