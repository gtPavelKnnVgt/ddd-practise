package com.study.medic.healersproject.app.api;

public class PatientAcceptedException extends RuntimeException {
    private static final String ERROR_MESSAGE = "patient did not accept request for appointment or medical record number is null. Could not save.";

    public PatientAcceptedException() {
        super(ERROR_MESSAGE);
    }
}
