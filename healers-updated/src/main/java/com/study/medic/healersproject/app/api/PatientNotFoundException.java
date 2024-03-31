package com.study.medic.healersproject.app.api;

public class PatientNotFoundException extends RuntimeException {
    private static final String ERROR_MESSAGE = "patient within id %s not found";

    public PatientNotFoundException(Long patientId) {
        super(String.format(ERROR_MESSAGE, patientId));
    }
}
