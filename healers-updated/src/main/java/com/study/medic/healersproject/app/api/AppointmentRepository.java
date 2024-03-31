package com.study.medic.healersproject.app.api;

import com.study.medic.healersproject.domain.Appointment;

public interface AppointmentRepository {
    /**
     * Сохранить запись
     *
     * @param appointment запись для сохранения
     * @return сохраненная запись
     */
    Appointment saveAndFlush(Appointment appointment);
}
