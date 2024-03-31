package com.study.medic.healersproject.app.impl;

import com.study.medic.healersproject.app.api.CreatePatientInbound;
import com.study.medic.healersproject.app.api.PatientRepository;
import com.study.medic.healersproject.domain.Patient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreatePatientUseCase implements CreatePatientInbound {
    private final PatientRepository patientRepository;

    @Transactional
    @Override
    public Patient create(String firstName, String lastName, LocalDate birthDate, String medicalRecordNumber) {
        log.info("Creating patient...");
        var patient = Patient.create(firstName, lastName, birthDate, medicalRecordNumber);
        return patientRepository.saveAndFlush(patient);
    }
}
