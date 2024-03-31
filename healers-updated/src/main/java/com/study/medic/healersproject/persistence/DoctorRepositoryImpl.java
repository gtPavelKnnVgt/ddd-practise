package com.study.medic.healersproject.persistence;

import com.study.medic.healersproject.app.api.DoctorNotFoundException;
import com.study.medic.healersproject.app.api.DoctorRepository;
import com.study.medic.healersproject.domain.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DoctorRepositoryImpl implements DoctorRepository {
    private final DoctorJpaRepository doctorJpaRepository;

    @Override
    public Doctor getById(Long doctorId) {
        return doctorJpaRepository.findDoctorById(doctorId)
                .orElseThrow(() -> new DoctorNotFoundException(doctorId));
    }

    @Override
    public Doctor saveAndFlush(Doctor doctor) {
        return doctorJpaRepository.saveAndFlush(doctor);
    }
}
