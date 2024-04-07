package com.study.medic.healersproject.app.api;

import com.study.medic.healersproject.domain.Department;
import com.study.medic.healersproject.domain.Doctor;
import com.study.medic.healersproject.domain.DoctorSpecialization;

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

    /**
     * Создать врача с рабочим графиком
     * @param firstName имя врача
     * @param lastName фамилия врача
     * @param specialization специализация врача
     * @param licenseNumber номер страховки врача
     * @param departmentId идентификатор отделения
     * @return созданный врач
     */
    Department createDoctor(String dayOfWeek, LocalDateTime startTime, LocalDateTime endTime, String firstName, String lastName, DoctorSpecialization specialization,
                        String licenseNumber, Long departmentId);
}
