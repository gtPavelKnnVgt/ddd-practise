package com.study.medic.healersproject.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DoctorSpecialization {
    THERAPIST("Терапевт"),
    SURGEON("Хирург"),
    OPHTHALMOLOGIST("Отфальмолог"),
    ECHOCARDIOLOGIST("Эхокардиолог"),
    NEUROLOGIST("Невролог");

    private final String value;
}
