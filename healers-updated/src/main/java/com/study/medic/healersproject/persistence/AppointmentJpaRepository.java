package com.study.medic.healersproject.persistence;

import com.study.medic.healersproject.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentJpaRepository extends JpaRepository<Appointment, Long> {
}
