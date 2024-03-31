package com.study.medic.healersproject.app.api;

import com.study.medic.healersproject.domain.Doctor;

public interface DoctorRepository {
    /**
     * Найти врача по идентификатору
     *
     * @param doctorId идентификатор врача
     * @return найденный врач
     */
    Doctor getById(Long doctorId);

    /**
     * Сохранить врача
     * @param doctor врач
     * @return сохраненный врач
     */
    Doctor saveAndFlush(Doctor doctor);
}
