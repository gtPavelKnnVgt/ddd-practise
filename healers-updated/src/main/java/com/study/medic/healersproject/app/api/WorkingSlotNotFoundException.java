package com.study.medic.healersproject.app.api;

public class WorkingSlotNotFoundException extends RuntimeException {
    private final static String ERROR_MESSAGE = "working slot withing doctor id %s not found";

    public WorkingSlotNotFoundException(Long doctorId) {
        super(String.format(ERROR_MESSAGE, doctorId));
    }
}
