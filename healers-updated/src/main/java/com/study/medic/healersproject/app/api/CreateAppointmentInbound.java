package com.study.medic.healersproject.app.api;

import com.study.medic.healersproject.domain.Appointment;
import com.study.medic.healersproject.domain.TimeSlot;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public interface CreateAppointmentInbound {
    /**
     * Создать запись на прием
     *
     * @param patientId привязанный пациент
     * @param doctorId привязанный доктор
     * @param startTime время для резервирования начала
     * @param endTime время для резервирования конца
     * @param isAccepted принял ли пациент запрос
     * @return созданная запись
     */
    Appointment create(Long patientId, Long doctorId, LocalDateTime startTime, LocalDateTime endTime, String dayOfWeek, boolean isAccepted);

    /**
     * Проверить возможно ли создание записи для пациента
     * В случае невозможности - выбрасывать исключение
     * @param patientId идентификатор пациента
     * @param startTime время начала записи
     * @param endTime время окончания записи
     * @param dayOfWeek день недели
     * @param isAccepted принял ли пациент эту запись
     */
    void validate(Long patientId,  LocalDateTime startTime, LocalDateTime endTime, String dayOfWeek, boolean isAccepted);
}
