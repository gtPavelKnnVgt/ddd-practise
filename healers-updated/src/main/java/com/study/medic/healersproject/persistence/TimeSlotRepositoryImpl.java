package com.study.medic.healersproject.persistence;

import com.study.medic.healersproject.app.api.TimeSlotRepository;
import com.study.medic.healersproject.domain.TimeSlot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TimeSlotRepositoryImpl implements TimeSlotRepository {
    private final TimeSlotJpaRepository timeSlotJpaRepository;

    @Override
    public TimeSlot saveAndFlush(TimeSlot timeSlot) {
        return timeSlotJpaRepository.saveAndFlush(timeSlot);
    }
}
