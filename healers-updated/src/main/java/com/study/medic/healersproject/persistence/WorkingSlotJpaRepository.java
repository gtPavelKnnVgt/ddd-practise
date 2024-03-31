package com.study.medic.healersproject.persistence;

import com.study.medic.healersproject.domain.WorkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WorkingSlotJpaRepository extends JpaRepository<WorkingSlot, Long> {
    @Query("""
                select ws from WorkingSlot ws
                join fetch ws.doctors dct
                where dct.id = :doctorId
            """)
    Optional<WorkingSlot> findByDoctorId(Long doctorId);
}
