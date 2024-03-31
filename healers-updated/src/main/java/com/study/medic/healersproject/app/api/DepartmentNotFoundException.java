package com.study.medic.healersproject.app.api;

public class DepartmentNotFoundException extends RuntimeException {
    public DepartmentNotFoundException(Long doctorId) {
        super(String.format("Department by doctor id: %s not found!", doctorId));
    }
}
