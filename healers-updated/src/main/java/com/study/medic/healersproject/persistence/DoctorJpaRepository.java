package com.study.medic.healersproject.persistence;

import com.study.medic.healersproject.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorJpaRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findDoctorById(Long doctorId);
}
