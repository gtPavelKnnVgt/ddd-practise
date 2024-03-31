package com.study.medic.healersproject.persistence;

import com.study.medic.healersproject.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientJpaRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findById(Long patientId);
}
