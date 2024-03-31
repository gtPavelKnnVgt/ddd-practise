package com.study.medic.healersproject.app.api;

import com.study.medic.healersproject.domain.WorkingSlot;

public interface WorkingSlotRepository {
    /**
     * Найти рабочий слот врача
     *
     * @param doctorId идентификатор врача
     * @return найденный рабочий слот врача
     */
    WorkingSlot getByDoctorId(Long doctorId);

    /**
     * Сохранить слот
     * @param workingSlot рабочий слот
     * @return сохраненный слот
     */
    WorkingSlot saveAndFlush(WorkingSlot workingSlot);
}
