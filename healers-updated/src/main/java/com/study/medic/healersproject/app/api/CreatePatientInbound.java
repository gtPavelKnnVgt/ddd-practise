package com.study.medic.healersproject.app.api;

import com.study.medic.healersproject.domain.Patient;

import java.time.LocalDate;

public interface CreatePatientInbound {
    /**
     * Создать пациента
     * @param firstName имя пациента
     * @param lastName фамилия пациента
     * @param birthDate дата рождения
     * @param medicalRecordNumber номер мед. полиса
     * @return сохраненный пациент
     */
    Patient create(String firstName, String lastName, LocalDate birthDate, String medicalRecordNumber);
}
