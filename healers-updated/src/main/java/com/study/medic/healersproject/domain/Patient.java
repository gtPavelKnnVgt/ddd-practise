package com.study.medic.healersproject.domain;

import com.study.medic.healersproject.app.api.Aggregator;
import com.study.medic.healersproject.app.api.PatientNotValidException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "patient")
@Builder
@Getter
@Slf4j
public class Patient extends DomainObject implements Aggregator {
    private static final String NAME_REGEX = "^[A-Za-z\\s]+$";
    private static final String NOT_VALID_RECORD_NUMBERS = "^(?!FHWR).*$";

    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
    @Column(name = "medical_record_number")
    private String medicalRecordNumber;
    @Transient
    @Setter
    private boolean isAccepted;

    protected Patient() {
        throw new UnsupportedOperationException("Creation via default constructor is not valid!");
    }

    public boolean hasAppointment(TimeSlot timeSlot, List<Appointment> appointments) {
        return appointments.stream()
                .anyMatch(appointment -> appointment.getWorkingSlot().getTimeSlot().overlaps(timeSlot));
    }

    public static Patient create(String firstName, String lastName, LocalDate birthDate, String medicalRecordNumber) {
        if (!isValidName(firstName) || !isValidName(lastName)) {
            throw new PatientNotValidException(firstName, lastName);
        }
        if (birthDate == null) {
            throw new PatientNotValidException();
        }
        if (medicalRecordNumber != null && !isValidMedicalRecordNumber(medicalRecordNumber)) {
            throw new PatientNotValidException();
        }
        return Patient.builder()
                .firstName(firstName)
                .lastName(lastName)
                .birthDate(birthDate)
                .medicalRecordNumber(medicalRecordNumber)
                .build();
    }

    private static boolean isValidName(String name) {
        Pattern pattern = Pattern.compile(NAME_REGEX);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    private static boolean isValidMedicalRecordNumber(String medicalRecordNumber) {
        Pattern pattern = Pattern.compile(NOT_VALID_RECORD_NUMBERS);
        Matcher matcher = pattern.matcher(medicalRecordNumber);
        return matcher.matches();
    }

    public static void notifyAppointmentTimeSlotChanged(Appointment appointment) {
        // Implement the logic to notify the patient about the change in the appointment time slot
        TimeSlot timeSlot = appointment.getWorkingSlot().getTimeSlot();
        log.info("Your appointment with a doctor: {} updated. Timeslot for: {} to {}", appointment.getDoctor().getFirstName()
                .concat(" ".concat(appointment.getDoctor().getLastName())), timeSlot.getStartTime(), timeSlot.getEndTime());
    }
}
