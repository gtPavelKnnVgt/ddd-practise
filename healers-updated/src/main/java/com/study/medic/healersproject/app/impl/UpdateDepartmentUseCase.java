package com.study.medic.healersproject.app.impl;

import com.study.medic.healersproject.app.api.*;
import com.study.medic.healersproject.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateDepartmentUseCase implements UpdateDepartmentInbound {
    private final DepartmentRepository departmentRepository;
    private final WorkingSlotRepository workingSlotRepository;
    private final TimeSlotRepository timeSlotRepository;
    private final AppointmentRepository appointmentRepository;

    @Override
    @Transactional
    public Department update(String dayOfWeek, LocalDateTime startTime, LocalDateTime endTime, Long doctorId) {
        log.info("Updating department by doctor id: {}", doctorId);
        Department department = departmentRepository.getDepartmentByDoctorId(doctorId);
        Doctor doctor = department.getDoctors().stream().filter(doc -> doctorId.equals(doc.getId()))
                .findFirst().orElseThrow(() -> new DoctorNotFoundException(doctorId));
        TimeSlot timeSlot = TimeSlot.create(startTime, endTime);
        WorkingSlot workingSlot = WorkingSlot.create(dayOfWeek, timeSlot);
        List<Appointment> appointmentsForCheck = appointmentRepository.findByDoctorId(doctor.getId());
        doctor.updateAvailability(workingSlot, appointmentsForCheck);
        return departmentRepository.saveAndFlush(department);
    }

    @Override
    @Transactional
    public Department createDoctor(String dayOfWeek, LocalDateTime startTime, LocalDateTime endTime, String firstName, String lastName, DoctorSpecialization specialization, String licenseNumber, Long departmentId) {
        log.info("Creating a doctor...");
        Department department = departmentRepository.getById(departmentId);
        TimeSlot timeSlot = TimeSlot.create(startTime, endTime);
        timeSlotRepository.saveAndFlush(timeSlot);
        WorkingSlot workingSlot = WorkingSlot.create(dayOfWeek, timeSlot);
        workingSlotRepository.saveAndFlush(workingSlot);
        var doctor = Doctor.create(workingSlot, firstName, lastName, specialization, licenseNumber);
        department.addDoctor(doctor);
        return departmentRepository.saveAndFlush(department);
    }
}
