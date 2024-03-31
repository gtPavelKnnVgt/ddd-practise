package com.study.medic.healersproject.persistence;

import com.study.medic.healersproject.app.api.WorkingSlotNotFoundException;
import com.study.medic.healersproject.app.api.WorkingSlotRepository;
import com.study.medic.healersproject.domain.WorkingSlot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WorkingSlotRepositoryImpl implements WorkingSlotRepository {
    private final WorkingSlotJpaRepository workingSlotJpaRepository;

    @Override
    public WorkingSlot getByDoctorId(Long doctorId) {
        return workingSlotJpaRepository.findByDoctorId(doctorId)
                .orElseThrow(() -> new WorkingSlotNotFoundException(doctorId));
    }

    @Override
    public WorkingSlot saveAndFlush(WorkingSlot workingSlot) {
        return workingSlotJpaRepository.saveAndFlush(workingSlot);
    }
}
