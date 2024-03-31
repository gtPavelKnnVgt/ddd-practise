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
}
