package com.study.medic.healersproject.app.api;

import com.study.medic.healersproject.domain.Patient;

public interface PatientRepository {
    /**
     * Найти пациента по идентификатору
     *
     * @param patientId идентификатор пациента
     * @return найденный пациент
     */
    Patient getById(Long patientId);

    /**
     * Сохранить пациента
     * @param patient пациент
     * @return сохраненный пациент
     */
    Patient saveAndFlush(Patient patient);
}
