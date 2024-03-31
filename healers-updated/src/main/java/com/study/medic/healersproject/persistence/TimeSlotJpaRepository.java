package com.study.medic.healersproject.persistence;

import com.study.medic.healersproject.domain.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSlotJpaRepository extends JpaRepository<TimeSlot, Long> {
}
