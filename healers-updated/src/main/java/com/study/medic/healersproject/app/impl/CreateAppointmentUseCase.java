package com.study.medic.healersproject.app.impl;

import com.study.medic.healersproject.app.api.*;
import com.study.medic.healersproject.domain.Appointment;
import com.study.medic.healersproject.domain.TimeSlot;
import com.study.medic.healersproject.domain.WorkingSlot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateAppointmentUseCase implements CreateAppointmentInbound {
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final TimeSlotRepository timeSlotRepository;

    @Override
    @Transactional
    public Appointment create(Long patientId, Long doctorId, LocalDateTime startTime, LocalDateTime endTime, String dayOfWeek, boolean isAccepted) {
        log.info("Creating appointment for patient id: {} with doctor id: {}", patientId, doctorId);
        var patient = patientRepository.getById(patientId);
        var doctor = doctorRepository.getById(doctorId);
        var validatedTimeSlot = TimeSlot.create(startTime, endTime);
        timeSlotRepository.saveAndFlush(validatedTimeSlot);
        WorkingSlot workingSlot = WorkingSlot.create(dayOfWeek, validatedTimeSlot);
        patient.setAccepted(isAccepted);
        var appointment = Appointment.create(patient, doctor, workingSlot);
        return appointmentRepository.saveAndFlush(appointment);
    }

    @Override
    @Transactional(readOnly = true)
    public void validate(Long patientId, LocalDateTime startTime, LocalDateTime endTime, String dayOfWeek, boolean isAccepted) {
        log.info("Validation of creation appointment by patient id: {}", patientId);
        var patient = patientRepository.getById(patientId);
        var validatedTimeSlot = TimeSlot.create(startTime, endTime);
        WorkingSlot workingSlot = WorkingSlot.create(dayOfWeek, validatedTimeSlot);
        patient.setAccepted(isAccepted);
        List<Appointment> appointmentsForCheck = appointmentRepository.findByPatientId(patientId);
        Appointment.tryToReserve(patient, workingSlot, appointmentsForCheck);
    }
}
