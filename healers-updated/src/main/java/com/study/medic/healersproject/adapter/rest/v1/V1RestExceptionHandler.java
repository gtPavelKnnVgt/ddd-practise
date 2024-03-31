package com.study.medic.healersproject.adapter.rest.v1;

import com.study.medic.healersproject.app.api.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class V1RestExceptionHandler extends ResponseEntityExceptionHandler {
    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal Server Error";

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(INTERNAL_SERVER_ERROR_MESSAGE);
    }

    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<Object> handleException(DoctorNotFoundException ex) {
        logger.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<Object> handleException(PatientNotFoundException ex) {
        logger.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(UnexpectedTimeSlotReserve.class)
    public ResponseEntity<Object> handleException(UnexpectedTimeSlotReserve ex) {
        logger.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(WorkingSlotNotFoundException.class)
    public ResponseEntity<Object> handleException(WorkingSlotNotFoundException ex) {
        logger.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(PatientAcceptedException.class)
    public ResponseEntity<Object> handleException(PatientAcceptedException ex) {
        logger.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(PatientNotValidException.class)
    public ResponseEntity<Object> handleException(PatientNotValidException ex) {
        logger.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(DoctorNotValidException.class)
    public ResponseEntity<Object> handleException(DoctorNotValidException ex) {
        logger.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<Object> handleException(DepartmentNotFoundException ex) {
        logger.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
