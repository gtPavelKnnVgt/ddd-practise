package com.study.medic.healersproject.app.api;

import com.study.medic.healersproject.domain.Department;

import java.time.LocalDateTime;

public interface UpdateDepartmentInbound {
    /**
     * Обновить информацию по департаменту
     * @param dayOfWeek день недели
     * @param startTime начала рабочего графика
     * @param endTime конец рабочего графика
     * @param doctorId идентификатор доктора
     * @return обновленный департамент
     */
    Department update(String dayOfWeek, LocalDateTime startTime, LocalDateTime endTime, Long doctorId);
}
