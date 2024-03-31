package com.study.medic.healersproject.app.api;

public class DoctorNotFoundException extends RuntimeException {
    private static final String ERROR_MESSAGE = "Doctor within id %s not found";

    public DoctorNotFoundException(Long doctorId) {
        super(String.format(ERROR_MESSAGE, doctorId));
    }
}
