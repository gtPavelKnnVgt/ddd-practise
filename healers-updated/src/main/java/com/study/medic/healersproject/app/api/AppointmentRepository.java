package com.study.medic.healersproject.app.api;

import com.study.medic.healersproject.domain.Appointment;

import java.util.List;

public interface AppointmentRepository {
    /**
     * Сохранить запись
     *
     * @param appointment запись для сохранения
     * @return сохраненная запись
     */
    Appointment saveAndFlush(Appointment appointment);

    /**
     * Найти все записи, в которых участвует врач
     * @param doctorId идентификатор врача
     * @return найденные записи
     */
    List<Appointment> findByDoctorId(Long doctorId);

    /**
     * Найти все записи, в которых участвует пациент
     * @param patientId идентификатор пациента
     * @return найденные записи
     */
    List<Appointment> findByPatientId(Long patientId);
}
