package com.study.medic.healersproject.domain;

import com.study.medic.healersproject.app.api.Aggregator;
import com.study.medic.healersproject.app.api.PatientAcceptedException;
import com.study.medic.healersproject.app.api.UnexpectedTimeSlotReserve;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "appointment")
@Builder
@Getter
public class Appointment extends DomainObject implements Aggregator {
    @OneToOne
    @JoinColumn(name = "working_slot_id")
    private WorkingSlot workingSlot;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    protected Appointment() {
        throw new UnsupportedOperationException("Creation via default constructor is not valid!");
    }

    public static Appointment create(Patient patient, Doctor doctor, WorkingSlot targetWorkingSlot) {
        WorkingSlot workingSlot = doctor.getWorkingSlot();
        TimeSlot doctorsTimeSlot = workingSlot.getTimeSlot();
        if (targetWorkingSlot.getTimeSlot().getStartTime().isBefore(doctorsTimeSlot.getStartTime()) ||
                targetWorkingSlot.getTimeSlot().getEndTime().isAfter(doctorsTimeSlot.getEndTime())) {
            throw new UnexpectedTimeSlotReserve(targetWorkingSlot.getTimeSlot().getId(), doctor.getId());
        }
        if (!patient.isAccepted() || patient.getMedicalRecordNumber() == null) {
            throw new PatientAcceptedException();
        }
        return Appointment.builder()
                .doctor(doctor)
                .patient(patient)
                .workingSlot(targetWorkingSlot)
                .build();
    }

    public static void tryToReserve(Patient patient, WorkingSlot workingSlot, List<Appointment> appointments) {
        if (patient.hasAppointment(workingSlot.getTimeSlot(), appointments)) {
            throw new IllegalStateException("Patient already has an appointment reservation for this time slot.");
        }
    }
}
