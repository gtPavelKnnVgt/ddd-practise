package com.study.medic.healersproject.persistence;

import com.study.medic.healersproject.app.api.PatientNotFoundException;
import com.study.medic.healersproject.app.api.PatientRepository;
import com.study.medic.healersproject.domain.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PatientRepositoryImpl implements PatientRepository {
    private final PatientJpaRepository patientJpaRepository;

    @Override
    public Patient getById(Long patientId) {
        return patientJpaRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException(patientId));
    }

    @Override
    public Patient saveAndFlush(Patient patient) {
        return patientJpaRepository.saveAndFlush(patient);
    }
}
