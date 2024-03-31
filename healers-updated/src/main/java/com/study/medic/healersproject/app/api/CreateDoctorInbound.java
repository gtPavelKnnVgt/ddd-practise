package com.study.medic.healersproject.app.api;

import com.study.medic.healersproject.domain.Doctor;
import com.study.medic.healersproject.domain.DoctorSpecialization;

import java.time.LocalDateTime;

public interface CreateDoctorInbound {
    /**
     * Создать врача с рабочим графиком
     * @param firstName имя врача
     * @param lastName фамилия врача
     * @param specialization специализация врача
     * @param licenseNumber номер страховки врача
     * @return
     */
    Doctor create(String dayOfWeek, LocalDateTime startTime, LocalDateTime endTime, String firstName, String lastName, DoctorSpecialization specialization,
                  String licenseNumber);
}
