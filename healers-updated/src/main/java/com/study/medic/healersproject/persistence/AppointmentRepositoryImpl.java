package com.study.medic.healersproject.persistence;

import com.study.medic.healersproject.app.api.AppointmentRepository;
import com.study.medic.healersproject.domain.Appointment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AppointmentRepositoryImpl implements AppointmentRepository {
    private final AppointmentJpaRepository appointmentJpaRepository;

    @Override
    public Appointment saveAndFlush(Appointment appointment) {
        return appointmentJpaRepository.saveAndFlush(appointment);
    }

    @Override
    public List<Appointment> findByDoctorId(Long doctorId) {
        return appointmentJpaRepository.findAllByDoctorId(doctorId);
    }

    @Override
    public List<Appointment> findByPatientId(Long patientId) {
        return appointmentJpaRepository.findAllByPatientId(patientId);
    }
}
