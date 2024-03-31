package com.study.medic.healersproject.app.api;

import com.study.medic.healersproject.domain.TimeSlot;

public interface TimeSlotRepository {
    /**
     * Сохранить временную рамку
     *
     * @param timeSlot временная рамка
     * @return сохраненная временная рамка
     */
    TimeSlot saveAndFlush(TimeSlot timeSlot);
}
